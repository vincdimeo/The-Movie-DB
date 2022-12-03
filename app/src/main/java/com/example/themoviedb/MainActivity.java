package com.example.themoviedb;

import android.os.Bundle;

import com.example.themoviedb.Classes.APICall;
import com.example.themoviedb.Classes.MoviesApiResponse;
import com.example.themoviedb.Classes.*;


import com.example.themoviedb.GUI.LoginFragment;
import com.example.themoviedb.GUI.ReleasesFragment;
import com.example.themoviedb.GUI.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.releases);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ReleasesFragment()).commit();
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.releases:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ReleasesFragment()).commit();
                        return true;

                    case R.id.search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SearchFragment()).commit();
                        return true;

                    case R.id.account:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LoginFragment()).commit();
                        return true;
                }

                APICall api = new APICall(MainActivity.this);
                api.getPopularMovies(listener, "it-IT");

                return false;
            }
            private final OnFetchDataListener<MoviesApiResponse> listener = new OnFetchDataListener<MoviesApiResponse>() {
                @Override
                public void onFetchData(List<Media> list, String message) {
                    if (list.isEmpty()) {
                        Toast.makeText(MainActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
                    } else{

                    }
                }

                @Override
                public void onError(String message) {
                    Toast.makeText(MainActivity.this, "An Error Occurred!", Toast.LENGTH_SHORT).show();
                }
            };



        });
    }


}