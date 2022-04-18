package com.agostina.mr.plantagram2.model.plants;

import java.util.ArrayList;

public class PlantDetails {

    private ArrayList<String> common_names;
    private String url;
    private WikiDescription wiki_description;

    public ArrayList<String> getCommon_names() {
        return common_names;
    }

    public String getUrl() {
        return url;
    }

    public WikiDescription getWiki_description() {
        return wiki_description;
    }
}
