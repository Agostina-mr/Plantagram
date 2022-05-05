package com.agostina.mr.plantagram2.model.plants;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.agostina.mr.plantagram2.model.User;

import java.util.ArrayList;
import java.util.Date;

@Entity(tableName = "plant")
public class Plant {
    @PrimaryKey(autoGenerate = true)
    private int dbId;
    private int id;
    private ArrayList<Images> images = new ArrayList<>();
    private ArrayList<Suggestions> suggestions = new ArrayList<>();
    private boolean is_plant;
    private double is_plant_probability;
    private String user_comment;


    private Date date = new Date();

    private User user = new User();

    public Plant() {
    }

    @Ignore
    public Plant(int id, ArrayList<Images> images, ArrayList<Suggestions> suggestions, boolean is_plant, double is_plant_probability) {
        this.id = id;
        this.images = images;
        this.suggestions = suggestions;
        this.is_plant = is_plant;
        this.is_plant_probability = is_plant_probability;
    }

    @Ignore
    public Plant(ArrayList<Images> images, ArrayList<Suggestions> suggestions, String user_comment) {
        this.images = images;
        this.suggestions = suggestions;
        this.user_comment = user_comment;
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

    public String getUserComment() {
        return user_comment;
    }

    public void setUserComment(String userComment) {
        this.user_comment = userComment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getDbId() {
        return dbId;
    }

    public String getUser_comment() {
        return user_comment;
    }

    public void setDbId(int dbId) {
        this.dbId = dbId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImages(ArrayList<Images> images) {
        this.images = images;
    }

    public void setSuggestions(ArrayList<Suggestions> suggestions) {
        this.suggestions = suggestions;
    }

    public void setIs_plant(boolean is_plant) {
        this.is_plant = is_plant;
    }

    public void setIs_plant_probability(double is_plant_probability) {
        this.is_plant_probability = is_plant_probability;
    }

    public void setUser_comment(String user_comment) {
        this.user_comment = user_comment;
    }
}
