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
    private String cover;
    private String backdrop;
    private String description;
    private String releaseDate;
    private String valutation;
    private String type;

    public Media(String id, String cover, String backdrop, String title, String description, String releaseDate, String valutation, String type) {
        this.id=id;
        this.cover = cover;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.valutation = valutation;
        this.backdrop = backdrop;
        setType(type);
    }

    public Media(){
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getBackdrop() { return backdrop; }

    public String getCover() {
        return cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getValutation() {
        return valutation;
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
