package com.example.themoviedb;
import java.util.List;

public interface OnFetchDataListener<NewsApiResponse> {
    void onFetchData(List<Media> list, String message);
    void onError(String message);
}
