package com.example.themoviedb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.themoviedb.Adapters.MediaAdapter;
import com.example.themoviedb.Adapters.ResultAdapter;
import com.example.themoviedb.Classes.APICall;
import com.example.themoviedb.Classes.Media;
import com.example.themoviedb.Classes.MoviesApiResponse;
import com.example.themoviedb.Classes.OnFetchDataListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ResultsActivity extends AppCompatActivity {

    private TextView querySearch;
    private RecyclerView resultsRV;
    private FloatingActionButton backBtn;
    private ResultAdapter resultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        String query = intent.getStringExtra("query");

        querySearch = findViewById(R.id.queryTitolo);
        resultsRV = findViewById(R.id.resultsRV);
        backBtn = findViewById(R.id.backBtn);


        resultsRV.setHasFixedSize(true);
        resultsRV.setLayoutManager(new LinearLayoutManager(this));

        querySearch.setText(query);

        APICall api = new APICall(this);
        api.getSearchResults(listener, "it-IT", query);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    final OnFetchDataListener<MoviesApiResponse> listener = new OnFetchDataListener<MoviesApiResponse>() {
        @Override
        public void onFetchData(List<Media> list, String message) {
            if (list.isEmpty()) {
                Toast.makeText(ResultsActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
            }
            else {
                if(resultAdapter == null){
                    System.out.println("***" + list.get(0).getTitle());
                    resultAdapter = new ResultAdapter((ArrayList<Media>) list, ResultsActivity.this);
                    resultsRV.setAdapter(resultAdapter);
                    System.out.println("***" + resultsRV.getAdapter().toString());

                }
            }
        }

        @Override
        public void onError(String message) {
            Toast.makeText(ResultsActivity.this, "An Error Occurred!", Toast.LENGTH_SHORT).show();
        }
    };
}