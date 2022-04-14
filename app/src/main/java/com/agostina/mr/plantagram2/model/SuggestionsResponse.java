package com.agostina.mr.plantagram2.model;

import java.util.ArrayList;

public class SuggestionsResponse {
    private int id;
    private String plant_name;
    private ArrayList<Suggestions> suggestions;

    public ArrayList<Suggestions> getSuggestionsList() {
        return suggestions;
    }
    public Suggestions getSuggestions(){
        return new Suggestions(id, plant_name);
    }
}
