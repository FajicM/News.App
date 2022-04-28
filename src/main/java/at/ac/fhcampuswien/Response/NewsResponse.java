package at.ac.fhcampuswien.Response;

import at.ac.fhcampuswien.models.Article;

import java.util.List;

public class NewsResponse {
    private String status;
    private int totalResult;
    private List<Article> articles;

    public String getStatus() {
        return status;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public List<Article> getArticles() {
        return articles;
    }
}
