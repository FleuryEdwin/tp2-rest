package fr.eni.tp2_rest.dao.mongo;

import fr.eni.tp2_rest.bo.Article;
import fr.eni.tp2_rest.dao.IDAOArticle;
import fr.eni.tp2_rest.dao.mock.SaveResultDAO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Profile("mongo")
@Component
public class ArticleDAOMongo implements IDAOArticle {

    private final ArticleMongoRepository articleMongoRepository;

    public ArticleDAOMongo(ArticleMongoRepository articleMongoRepository) {
        this.articleMongoRepository = articleMongoRepository;
    }

    @Override
    public List<Article> getAllArticles() {
        return articleMongoRepository.findAll();
    }

    @Override
    public Article getArticleById(String id) {
        return articleMongoRepository.findById(id).stream().findFirst().orElse(null);
    }

    @Override
    public boolean deleteArticleById(String id) {
        if (!articleMongoRepository.existsById(id)) {
            return false;
        }
        articleMongoRepository.deleteById(id);
        return !articleMongoRepository.existsById(id);
    }

    @Override
    public SaveResultDAO<Article> save(Article article) {
        SaveResultDAO<Article> result = new SaveResultDAO<>();

        if(!articleMongoRepository.existsById(article.id)) {
            articleMongoRepository.save(article);

            result.data = article;
            result.isCreated = false;

            return result;
        }

        articleMongoRepository.save(article);

        result.data = article;
        result.isCreated = true;

        return result;
    }
}
