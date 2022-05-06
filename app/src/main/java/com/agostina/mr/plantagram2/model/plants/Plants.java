package com.agostina.mr.plantagram2.model.plants;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.agostina.mr.plantagram2.model.User;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "plant")
public class Plants {
    @PrimaryKey(autoGenerate = true)
    private int dbId;
    private int id;
    @Ignore
    private List<Images> images = new ArrayList<>();
    private boolean is_plant;
    private double is_plant_probability;
    @Ignore
    private List<Suggestions> suggestions = new ArrayList<>();
    private User user = new User();
    private String userComment;
    private String user_comment;
    //private String curveName;

    public Plants() {
    }

    @Ignore
    public Plants(int id, ArrayList<Images> images, ArrayList<Suggestions> suggestions, boolean is_plant, double is_plant_probability) {
        this.id = id;
        this.images = images;
        this.suggestions = suggestions;
        this.is_plant = is_plant;
        this.is_plant_probability = is_plant_probability;
    }

    @Ignore
    public Plants(ArrayList<Images> images, ArrayList<Suggestions> suggestions, String user_comment) {
        this.images = images;
        this.suggestions = suggestions;
        this.user_comment = user_comment;
    }

    public int getId() {
        return id;
    }

    public List<Images> getImages() {
        return images;
    }

    public List<Suggestions> getSuggestions() {
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

    public void setIs_plant(boolean is_plant) {
        this.is_plant = is_plant;
    }

    public void setIs_plant_probability(double is_plant_probability) {
        this.is_plant_probability = is_plant_probability;
    }

    public void setUser_comment(String user_comment) {
        this.user_comment = user_comment;
    }

    /*public String getCurveName() {
        return curveName;
    }

    public void setCurveName(String curveName) {
        this.curveName = curveName;
    }*/

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public void setSuggestions(List<Suggestions> suggestions) {
        this.suggestions = suggestions;
    }


}
