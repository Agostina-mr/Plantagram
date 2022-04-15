package com.agostina.mr.plantagram2.model.plants;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PlantResponse {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("images")
    @Expose
    private ArrayList<ImagesResponse> images;

    public PlantResponse() { }

    public ArrayList<ImagesResponse> getImages() {
        return images;
    }
    public int getId() {
        return id;
    }

}
