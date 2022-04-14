package com.agostina.mr.plantagram2.model;

import java.util.ArrayList;

public class Plant {

    private int id;
    private ArrayList<Suggestions> suggestions;

    public Plant(int id, ArrayList<Suggestions> suggestions) {
        this.id = id;
        this.suggestions = suggestions;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Suggestions> getSuggestions() {
        return suggestions;
    }
}
