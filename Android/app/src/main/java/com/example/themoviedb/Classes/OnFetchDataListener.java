package com.example.themoviedb.Classes;
import java.util.List;

public interface OnFetchDataListener<MovieApiResponse> {
    void onFetchData(List<Media> list, String message);
    void onError(String message);
}
