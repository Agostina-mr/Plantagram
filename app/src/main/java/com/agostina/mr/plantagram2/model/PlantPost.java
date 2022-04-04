package com.agostina.mr.plantagram2.model;

public class PlantPost {
    private String description;
    private String name;
    private int imageId;
    private String location;
    private User user;

    public PlantPost(String description, String name, int imageId, String location, User user) {
        this.description = description;
        this.name = name;
        this.imageId = imageId;
        this.location = location;
        this. user = user;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
