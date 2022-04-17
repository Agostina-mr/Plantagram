package com.agostina.mr.plantagram2.model.plants.responses;

import com.agostina.mr.plantagram2.model.plants.Images;
import com.agostina.mr.plantagram2.model.plants.Plant;
import com.agostina.mr.plantagram2.model.plants.Suggestions;

import java.util.ArrayList;

public class PlantResponse {
    private int id;
    private ArrayList<Images> images = new ArrayList<>();
    private ArrayList<Suggestions> suggestions = new ArrayList<>();
    private boolean is_plant;
    private double is_plant_probability;

    public Plant getPlant()
    {
        return new Plant(id, images, suggestions, is_plant, is_plant_probability);
    }
    public ArrayList<Suggestions> getSuggestions() {
        return suggestions;
    }
    public ArrayList<Images> getImages() {
        return images;
    }
    public int getId() {
        return id;
    }
    public boolean isIs_plant() {
        return is_plant;
    }

    public double getIs_plant_probability() {
        return is_plant_probability;
    }
}
