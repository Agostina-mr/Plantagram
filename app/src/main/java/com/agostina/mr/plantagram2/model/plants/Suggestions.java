package com.agostina.mr.plantagram2.model.plants;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "suggestions")
public class Suggestions {
    @PrimaryKey(autoGenerate = true)
    private int dbId;
    private int id;
    private String plant_name;
    private PlantDetails plant_details;

    public int getId() {
        return id;
    }

    public String getPlant_name() {
        return plant_name;
    }

    public PlantDetails getPlant_details() {
        return plant_details;
    }

    public int getDbId() {
        return dbId;
    }

    public void setDbId(int dbId) {
        this.dbId = dbId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlant_name(String plant_name) {
        this.plant_name = plant_name;
    }

    public void setPlant_details(PlantDetails plant_details) {
        this.plant_details = plant_details;
    }
}
