package com.example.themoviedb;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;

import com.example.themoviedb.Classes.APICall;
import com.example.themoviedb.Classes.MoviesApiResponse;
import com.example.themoviedb.Classes.*;


import com.example.themoviedb.GUI.AccountFragment;
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

    public static Utente utente = new Utente(null, null, null);
    public static String tema = "";
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("Utente", MODE_PRIVATE);
        Utente u = new Utente(
                sharedPreferences.getString("Username", ""),
                sharedPreferences.getString("Password", ""),
                sharedPreferences.getBoolean("Logged", false)
                );

        utente = u;
        tema = sharedPreferences.getString("Tema", "Nessuno");

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
                        if (utente.isLogged()) {
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AccountFragment()).commit();
                        }
                        else {
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LoginFragment()).commit();
                        }

                        return true;
                }

                return false;
            }
        });

        switch (tema) {
            case "Nessuno":
                break;

            case "Scala di grigi":
                setGreyScaleBar();
                break;

            case "Filtro rosso/verde":
                setProtanopiaBar();
                break;

            case "Filtro verde/rosso":
                setDaltonismoBar();
                break;

            case "Filtro blu/giallo":
                setTritanopiaBar();
                break;

            case "Testo grande":
                break;
        }
    }

    private void setTritanopiaBar() {
        int[][] states = new int[][] {
                new int[] { android.R.attr.state_checked}, // state_checked
                new int[] { }  //
        };

        int[] colors = new int[] {
                getColor(R.color.accentColor2_Tritanopia),
                getColor(R.color.accentColor3_Tritanopia)
        };

        ColorStateList myColorList = new ColorStateList(states, colors);
        bottomNav.setItemIconTintList(myColorList);
        bottomNav.setItemTextColor(myColorList);
    }

    private void setDaltonismoBar() {
        int[][] states = new int[][] {
                new int[] { android.R.attr.state_checked}, // state_checked
                new int[] { }  //
        };

        int[] colors = new int[] {
                getColor(R.color.accentColor2_Daltonismo),
                getColor(R.color.accentColor3_Daltonismo)
        };

        ColorStateList myColorList = new ColorStateList(states, colors);
        bottomNav.setItemIconTintList(myColorList);
        bottomNav.setItemTextColor(myColorList);
    }

    private void setProtanopiaBar() {
        int[][] states = new int[][] {
                new int[] { android.R.attr.state_checked}, // state_checked
                new int[] { }  //
        };

        int[] colors = new int[] {
                getColor(R.color.accentColor2_Protanopia),
                getColor(R.color.accentColor3_Protanopia)
        };

        ColorStateList myColorList = new ColorStateList(states, colors);
        bottomNav.setItemIconTintList(myColorList);
        bottomNav.setItemTextColor(myColorList);
    }

    private void setGreyScaleBar() {
        int[][] states = new int[][] {
                new int[] { android.R.attr.state_checked}, // state_checked
                new int[] { }  //
        };

        int[] colors = new int[] {
                getColor(R.color.accentColor2_GreyScale),
                getColor(R.color.accentColor3_GreyScale)
        };

        ColorStateList myColorList = new ColorStateList(states, colors);
        bottomNav.setItemIconTintList(myColorList);
        bottomNav.setItemTextColor(myColorList);
    }
}