package fr.eni.tp2_rest.dao;

import fr.eni.tp2_rest.bo.Article;
import fr.eni.tp2_rest.dao.mock.SaveResultDAO;

import java.util.List;

public interface IDAOArticle {
    List<Article> getAllArticles();

    Article getArticleById(String id);

    boolean deleteArticleById(String id);

    SaveResultDAO<Article> save(Article article);
}
