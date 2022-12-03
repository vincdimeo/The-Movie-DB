package com.example.themoviedb.Classes;



import java.io.Serializable;
import java.util.List;

public class MoviesApiResponse implements Serializable {
    String status;
    int totalResults;
    List<Media> results;

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

    public List<Media> getMovies() {
        return results;
    }

    public void setMovies(List<Media> results) {
        this.results = results;
    }
}
