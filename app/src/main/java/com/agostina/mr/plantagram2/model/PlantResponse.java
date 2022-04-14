package com.agostina.mr.plantagram2.model;

import android.provider.MediaStore;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PlantResponse {
    @SerializedName("id")
    private int id;
    @SerializedName("images")
    private ArrayList<Images> images;
    /*private ArrayList<Suggestions> suggestions;
    private ArrayList<Plant> Response;*/

  /*  public int getId() {
        return id;
    }*/

    public ArrayList<Images> getImages() {
        return images;
    }

    public int getId() {
        return id;
    }

    /*

    public ArrayList<Suggestions> getSuggestions() {
        return suggestions;
    }

    public ArrayList<Plant> getPlants() {
        return Response;
    }
    public Plant getPlant() {
        return new Plant(id, suggestions);
    }*/
}
