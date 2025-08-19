package fr.eni.tp2_rest.service;

import fr.eni.tp2_rest.bo.Article;
import fr.eni.tp2_rest.dao.ArticleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleDAO articleDAO;

    public ServiceResponse<List<Article>> getAllArticles() {
        ServiceResponse<List<Article>> serviceResponse = new ServiceResponse<>();

        return ServiceHelper.buildResponse(202, articleDAO.getAllArticles());
    }

    public ServiceResponse<Article> getArticleById(int id) {
        Article article = articleDAO.getArticleById(id);

        if(article == null) {
            return ServiceHelper.buildResponse(703, null);
        }

        return ServiceHelper.buildResponse(202, article);
    }

    public ServiceResponse<Article> deleteArticleById(int id) {
        boolean removeSuccess = articleDAO.deleteArticleById(id);

        if(!removeSuccess) {
            return ServiceHelper.buildResponse(703, null);
        }

        return ServiceHelper.buildResponse(202, null);
    }

    public ServiceResponse<Article> saveArticle(Article article) {
        // TODO
        // deux DAO (create/update)

        // une DAO (create/update selon id)
        Article savedArticle = articleDAO.saveArticle(article);

        return ServiceHelper.buildResponse(202, savedArticle);
    }

}
