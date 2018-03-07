package com.busradeniz.newsapplication.api;

import java.util.List;

/**
 * Created by busradeniz on 07/03/2018.
 */
public class ArticleListApiResponse {

    private final String status;
    private final int totalResults;
    private final List<Article> articles;

    public ArticleListApiResponse(String status, int totalResults, List<Article> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }
}
