package fr.eni.tp2_rest.bo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "articles")
public class Article {
    public Article() {

    }

    public Article(String id, String title) {
        this.id = id;
        this.title = title;
    }

    @Id
    public String id;

    public String title;
}
