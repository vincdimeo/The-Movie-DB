package com.example.themoviedb;

import java.io.Serializable;
import java.util.List;

public class MoviesApiResponse implements Serializable {
    String status;
    int totalResults;
    List<Media> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<Media> getArticles() {
        return articles;
    }

    public void setArticles(List<Media> articles) {
        this.articles = articles;
    }
}
