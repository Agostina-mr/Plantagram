package com.agostina.mr.plantagram2.model.plants;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "wiki_description")
public class WikiDescription {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String value;
    private String citation;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getCitation() {
        return citation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCitation(String citation) {
        this.citation = citation;
    }
}
