package fr.eni.tp2_rest.dao.mongo;

import fr.eni.tp2_rest.bo.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleMongoRepository extends MongoRepository<Article, String> {
}
