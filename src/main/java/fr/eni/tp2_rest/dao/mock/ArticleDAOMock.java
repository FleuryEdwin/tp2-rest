package fr.eni.tp2_rest.dao.mock;

import fr.eni.tp2_rest.bo.Article;
import fr.eni.tp2_rest.dao.IDAOArticle;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Profile("mock")
@Component
public class ArticleDAOMock implements IDAOArticle {

    // mock articleDB
    public List<Article> DB_ARTICLES;

    public ArticleDAOMock() {
        DB_ARTICLES = new ArrayList<>();

        // generer 10 articles
        for(int i = 0; i < 10; i++) {
            Article a = new Article();

            a.id = String.valueOf(i);
            a.title = String.format("Article n°%d", i);

            DB_ARTICLES.add(a);
        }
    }

    public List<Article> getAllArticles()
    {
        return DB_ARTICLES;
    }

    public Article getArticleById(String id)
    {
        // findFirt renvoi un optionnal
        Optional<Article> foundArticle = DB_ARTICLES.stream().filter(article -> article.id.equals(id)).findFirst();

        return foundArticle.orElse(null);
    }

    public boolean deleteArticleById(String id)
    {
        return DB_ARTICLES.removeIf(article -> article.id.equals(id));
    }

    public SaveResultDAO<Article> save(Article article){

        SaveResultDAO<Article> result = new SaveResultDAO<>();

        // sinon mettre à jour
        // -- Si article existe ne base alors le modifier
        Optional<Article> foundArticle = DB_ARTICLES.stream().filter(value -> value.id.equals(article.id)).findFirst();

        // si existe -> alors je le modifie
        if (foundArticle.isPresent()){
            foundArticle.get().title = article.title;

            result.isCreated = false;
            result.data = foundArticle.get();

            return result;
        }

        // Si id == null
        // générer un faux id
        article.id = String.valueOf(DB_ARTICLES.size() + 100);
        // Je ajoute l'article dans le tableau
        DB_ARTICLES.add(article);

        result.isCreated = true;
        result.data = article;

        return result;

    }
}