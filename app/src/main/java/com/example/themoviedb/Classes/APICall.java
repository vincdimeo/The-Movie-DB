package com.example.themoviedb.Classes;

import android.content.Context;
import android.widget.Toast;

import com.example.themoviedb.OnFetchDataListener;
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
            .baseUrl("https://api.themoviedb.org/3/movie")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void getPopularMovies(OnFetchDataListener listener, String language) {
        CallMoviesApi callMoviesApi = retrofit.create(CallMoviesApi.class);
        Call<MoviesApiResponse> call = callMoviesApi.callHeadlines(context.getString(R.string.api_key), language);

        try {
            call.enqueue(new Callback<MoviesApiResponse>() {
                @Override
                public void onResponse(Call<MoviesApiResponse> call, Response<MoviesApiResponse> response) {
                    if(!response.isSuccessful()) {
                        Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show();
                    }
                    listener.onFetchData(response.body().getArticles(), response.message());
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

    public void RequestManager(Context context) {
        this.context = context;
    }

    public interface CallMoviesApi {
        @GET("popular")
        Call<MoviesApiResponse> callHeadlines(
                @Query("api_key") String api_key,
                @Query("language") String language

        );
    }
}
