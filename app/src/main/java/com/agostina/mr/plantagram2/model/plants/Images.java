package com.agostina.mr.plantagram2.model.plants;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = ("images"))
public class Images {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String file_name;
    private String url;
    public String getFile_name() {
        return file_name;
    }

    public String getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
