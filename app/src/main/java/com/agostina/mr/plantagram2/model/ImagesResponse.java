package com.agostina.mr.plantagram2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ImagesResponse {
    @SerializedName("file_name")
    @Expose
    private String file_name;
    @SerializedName("url")
    @Expose
    private String url;

    public ImagesResponse() {

    }

    public Images getImages(){
        return new Images(file_name, url);
    }

    public String getFile_name() {
        return file_name;
    }

    public String getUrl() {
        return url;
    }
}
