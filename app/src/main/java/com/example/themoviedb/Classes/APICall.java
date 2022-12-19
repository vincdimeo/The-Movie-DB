package com.example.themoviedb.Classes;

import android.content.Context;
import android.widget.Toast;


import com.example.themoviedb.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


public class APICall {
    Context context;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    String api_key;

    public void getPopularMovies(OnFetchDataListener listener, String language) {
        CallMoviesApi callMoviesApi = retrofit.create(CallMoviesApi.class);
        Call<MoviesApiResponse> call = callMoviesApi.callPopular(api_key, language);

        try {
            call.enqueue(new Callback<MoviesApiResponse>() {
                @Override
                public void onResponse(Call<MoviesApiResponse> call, Response<MoviesApiResponse> response) {
                    if(!response.isSuccessful()) {
                        Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show();
                    }
                    listener.onFetchData(response.body().getMovies(), response.message());
                }

                @Override
                public void onFailure(Call<MoviesApiResponse> call, Throwable t) {
                    listener.onError("Request Failed!");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getUpcomingMovies(OnFetchDataListener listener, String language) {
        CallMoviesApi callMoviesApi = retrofit.create(CallMoviesApi.class);
        Call<MoviesApiResponse> call = callMoviesApi.callUpcoming(api_key, language);

        try {
            call.enqueue(new Callback<MoviesApiResponse>() {
                @Override
                public void onResponse(Call<MoviesApiResponse> call, Response<MoviesApiResponse> response) {
                    if(!response.isSuccessful()) {
                        Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show();
                    }
                    listener.onFetchData(response.body().getMovies(), response.message());
                }

                @Override
                public void onFailure(Call<MoviesApiResponse> call, Throwable t) {
                    listener.onError("Request Failed!");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getSearchResults(OnFetchDataListener listener, String language, String search) {
        CallMoviesApi callMoviesApi = retrofit.create(CallMoviesApi.class);
        Call<MoviesApiResponse> call = callMoviesApi.callSearch(api_key, language, search);

        try {
            call.enqueue(new Callback<MoviesApiResponse>() {
                @Override
                public void onResponse(Call<MoviesApiResponse> call, Response<MoviesApiResponse> response) {
                    if(!response.isSuccessful()) {
                        Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show();
                    }
                    listener.onFetchData(response.body().getMovies(), response.message());
                }

                @Override
                public void onFailure(Call<MoviesApiResponse> call, Throwable t) {
                    listener.onError("Request Failed!");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public APICall(Context context) {
        this.context = context;
        api_key = context.getString(R.string.api_key);
    }

    public interface CallMoviesApi {
        @GET("popular")
        Call<MoviesApiResponse> callPopular(
                @Query("api_key") String api_key,
                @Query("language") String language
        );

        @GET("upcoming")
        Call<MoviesApiResponse> callUpcoming(
                @Query("api_key") String api_key,
                @Query("language") String language
        );

        @GET("search")
        Call<MoviesApiResponse> callSearch(
                @Query("api_key") String api_key,
                @Query("language") String language,
                @Query("query") String search
        );
    }
}
