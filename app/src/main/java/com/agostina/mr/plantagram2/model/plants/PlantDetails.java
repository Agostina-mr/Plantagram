package com.agostina.mr.plantagram2.model.plants;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "plant_details")
public class PlantDetails {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private ArrayList<String> common_names;
    private String url;
    private WikiDescription wiki_description;

    public PlantDetails() {
    }

    public ArrayList<String> getCommon_names() {
        return common_names;
    }

    public String getUrl() {
        return url;
    }

    public WikiDescription getWiki_description() {
        return wiki_description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCommon_names(ArrayList<String> common_names) {
        this.common_names = common_names;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setWiki_description(WikiDescription wiki_description) {
        this.wiki_description = wiki_description;
    }
}
