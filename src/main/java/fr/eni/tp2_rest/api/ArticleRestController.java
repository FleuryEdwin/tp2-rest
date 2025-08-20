package fr.eni.tp2_rest.api;

import fr.eni.tp2_rest.bo.Article;
import fr.eni.tp2_rest.service.ArticleService;
import fr.eni.tp2_rest.service.ServiceResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/articles")
public class ArticleRestController {

    private final ArticleService articleService;

    public ArticleRestController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Operation(summary = "Récupérer tout les articles")
    @GetMapping("/all")
    public ServiceResponse getAllArticles() {
        return articleService.getAllArticles();
    }

    @Operation(summary = "Récupérer un article par son id")
    @GetMapping("/{id}")
    public ServiceResponse getArticleById(@PathVariable String id) {
        return articleService.getArticleById(id);
    }

    @Operation(summary = "Supprimer un article via l'id renseigner dans l'url")
    @DeleteMapping("/{id}")
    public ServiceResponse deleteArticleById(@PathVariable String id) {

        return articleService.deleteArticleById(id);
    }

    @Operation(summary = "Enregistrer un article, si il existe déja le modifie")
    @PostMapping()
    public ServiceResponse addArticle(@RequestBody Article article) {
        return articleService.saveArticle(article);
    }
}
