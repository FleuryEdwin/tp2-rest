package fr.eni.tp2_rest.dao;

import fr.eni.tp2_rest.bo.Article;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ArticleDAO {

    // mock articleDB
    public List<Article> DB_ARTICLES;

    public ArticleDAO() {
        DB_ARTICLES = new ArrayList<>();

        // generer 10 articles
        for(int i = 0; i < 10; i++) {
            Article a = new Article();

            a.id = i;
            a.title = String.format("Article n°%d", i);

            DB_ARTICLES.add(a);
        }
    }

    public List<Article> getAllArticles()
    {
        return DB_ARTICLES;
    }

    public Article getArticleById(int id)
    {
        // findFirt renvoi un optionnal
        Optional<Article> foundArticle = DB_ARTICLES.stream().filter(article -> article.id == id).findFirst();

        return foundArticle.orElse(null);
    }

    public boolean deleteArticleById(int id)
    {
        return DB_ARTICLES.removeIf(article -> article.id == id);
    }

    public Article saveArticle(Article article)
    {
        Optional<Article> foundArticle = DB_ARTICLES.stream().filter(value -> value.id == article.id).findFirst();

        if(foundArticle.isPresent()) {
            foundArticle.get().title = article.title;
            return foundArticle.get();
        }

        article.id = DB_ARTICLES.size() + 10;
        DB_ARTICLES.add(article);

        return article;
    }


}
