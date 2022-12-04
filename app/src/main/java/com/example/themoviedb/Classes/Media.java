package com.example.themoviedb.Classes;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Media implements Serializable {
    private String id;
    private String title;
    private String poster_path;
    private String backdrop_path;
    private String overview;
    private String release_date;
    private String vote_average;
    private String type;

    public Media(String id, String cover, String backdrop, String title, String description, String releaseDate, String valutation, String type) {
        this.id=id;
        this.poster_path = cover;
        this.title = title;
        this.overview = description;
        this.release_date = releaseDate;
        this.vote_average = valutation;
        this.backdrop_path = backdrop;
        setType(type);
    }

    public Media(){
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getBackdrop() { return backdrop_path; }

    public String getCover() {
        return poster_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return overview;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public String getValutation() {
        return vote_average;
    }

    public String getType() { return type; }

    public void setType(String type) {
        switch (type){
            case "movie":
                this.type = "Film";
                break;

            case "tv":
                this.type = "Serie TV";
                break;

            case "person":
                this.type = "Persona";
                break;

            default:
                this.type=type;
        }
    }
}
