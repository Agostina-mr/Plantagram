package com.agostina.mr.plantagram2.model.plants;

import java.util.ArrayList;

public class Plant {

    private int id;
    private ArrayList<Images> images = new ArrayList<>();
    private ArrayList<Suggestions> suggestions = new ArrayList<>();
    private boolean is_plant;
    private double is_plant_probability;

    public Plant(int id, ArrayList<Images> images, ArrayList<Suggestions> suggestions, boolean is_plant, double is_plant_probability) {
        this.id = id;
        this.images = images;
        this.suggestions = suggestions;
        this.is_plant = is_plant;
        this.is_plant_probability = is_plant_probability;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Images> getImages() {
        return images;
    }

    public ArrayList<Suggestions> getSuggestions() {
        return suggestions;
    }

    public boolean isIs_plant() {
        return is_plant;
    }

    public double getIs_plant_probability() {
        return is_plant_probability;
    }
}
