package fr.eni.tp2_rest;

import fr.eni.tp2_rest.bo.Article;
import fr.eni.tp2_rest.dao.ArticleDAO;
import fr.eni.tp2_rest.service.ArticleService;
import fr.eni.tp2_rest.service.ServiceHelper;
import fr.eni.tp2_rest.service.ServiceResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

@SpringBootTest
class Tp2RestApplicationTests {

	@Autowired
	ArticleService articleService;

	@Test
	void contextLoads() {
	}

	@Test
	void getAllArticles_test() {
		ServiceResponse<List<Article>> result_1 = articleService.getAllArticles();

		assertThat(result_1.code).isEqualTo(202);
	}

	@Test
	void getArticleById_test() {
		ServiceResponse<Article> result_1 = articleService.getArticleById(1);
		ServiceResponse<Article> result_2 = articleService.getArticleById(52000);

		assertThat(result_1.code).isEqualTo(202);
		assertThat(result_2.code).isEqualTo(703);
	}

	@Test
	void deleteArticleById_test() {
		ServiceResponse<Article> result_1 = articleService.getArticleById(1);
		ServiceResponse<Article> result_2 = articleService.getArticleById(52000);

		assertThat(result_1.code).isEqualTo(202);
		assertThat(result_2.code).isEqualTo(703);
	}

	@Test
	void saveArticle_test() {
		// Cas 1 - 202 (Creation)
		Article newArticle = new Article(1596, "Titre test");
		assertThat(articleService.saveArticle(newArticle).code).isEqualTo(202);

		// Cas 2 - 203 (Article existant à modifier)
		Article foundArticle = articleService.getArticleById(1).data;
		foundArticle.title = "Nouveau titre";

		assertThat(articleService.saveArticle(foundArticle).code).isEqualTo(203);

	}

}
