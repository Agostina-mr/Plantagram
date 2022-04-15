package com.agostina.mr.plantagram2.model.plants;

public class Suggestions {

    private int id;
    private String plant_name;

    public Suggestions(int id, String plant_name) {
        this.id = id;
        this.plant_name = plant_name;
    }

    public int getId() {

        return id;
    }

    public String getPlant_name() {
        return plant_name;
    }
}
