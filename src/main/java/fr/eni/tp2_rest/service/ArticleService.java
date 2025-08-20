package fr.eni.tp2_rest.service;

import fr.eni.tp2_rest.bo.Article;
import fr.eni.tp2_rest.dao.ArticleDAO;
import fr.eni.tp2_rest.dao.SaveResultDAO;
import fr.eni.tp2_rest.locale.LocaleHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleDAO articleDAO;
    @Autowired
    MessageSource messageSource;
    @Autowired
    private LocaleHelper localeHelper;

    public ServiceResponse<List<Article>> getAllArticles() {
        ServiceResponse<List<Article>> serviceResponse = new ServiceResponse<>();

        String messageResponse = messageSource.getMessage("GetAllArticle_202_Success", null, LocaleContextHolder.getLocale());

        return ServiceHelper.buildResponse(202, messageResponse, articleDAO.getAllArticles());
    }

    public ServiceResponse<Article> getArticleById(int id) {
        Article article = articleDAO.getArticleById(id);

        if(article == null) {
            String messageResponse = localeHelper.i18n("GetArticleById_703_NotFound");
            return ServiceHelper.buildResponse(703, messageResponse, null);
        }

        String messageResponse = localeHelper.i18n("GetArticleById_202_Success");
        return ServiceHelper.buildResponse(202, messageResponse, article);
    }

    public ServiceResponse<Article> deleteArticleById(int id) {
        boolean removeSuccess = articleDAO.deleteArticleById(id);

        if(!removeSuccess) {
            String messageResponse = localeHelper.i18n("DeleteArticle_703_NotFound");
            return ServiceHelper.buildResponse(703, messageResponse,null);
        }

        String messageResponse = localeHelper.i18n("DeleteArticle_202_Success");
        return ServiceHelper.buildResponse(202, messageResponse,null);
    }

    public ServiceResponse<Article> saveArticle(Article article) {
        // J'ai une seule DAO (qui fait create ou update selon l'id)
        SaveResultDAO<Article> saveResultDAO = articleDAO.save(article);

        if (saveResultDAO.isCreated){
            String messageResponse = localeHelper.i18n("UpdateArticle_203_Success");
            return ServiceHelper.buildResponse(202, messageResponse, saveResultDAO.data);
        }

        String messageResponse = localeHelper.i18n("AddArticle_202_Success");
        return ServiceHelper.buildResponse(203, messageResponse, saveResultDAO.data);
    }

}
