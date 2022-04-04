package com.agostina.mr.plantagram2.model;

public class User {

    private String userName;
    private String password;
    private int imageId;

    public User(String userName, String password, int imageId) {
        this.userName = userName;
        this.password = password;
        this.imageId = imageId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
