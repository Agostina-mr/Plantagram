package com.agostina.mr.plantagram2.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    /*private String userName;
    private String password;
    private String image;
    @Ignore
    private Plants plants;*/

    public User() {
    }

    /*public Plants getPlant() {
        return plants;
    }

    public void setPlant(Plants plants) {
        this.plants = plants;
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
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    /*public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }*/
}
