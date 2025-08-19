package fr.eni.tp2_rest.api;

import fr.eni.tp2_rest.bo.Article;
import fr.eni.tp2_rest.service.ArticleService;
import fr.eni.tp2_rest.service.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/articles")
public class ArticleRestController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/all")
    public ServiceResponse getAllArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/{id}")
    public ServiceResponse getArticleById(@PathVariable String id) {
        int idInt = Integer.parseInt(id);

        return articleService.getArticleById(idInt);
    }

    @DeleteMapping("/{id}")
    public ServiceResponse deleteArticleById(@PathVariable String id) {
        int idInt = Integer.parseInt(id);

        return articleService.deleteArticleById(idInt);
    }

    @PostMapping()
    public ServiceResponse addArticle(@RequestBody Article article) {
        return articleService.saveArticle(article);
    }
}
