package com.agostina.mr.plantagram2.room;


import androidx.lifecycle.LiveData;
import androidx.room.TypeConverter;

import com.agostina.mr.plantagram2.model.User;
import com.agostina.mr.plantagram2.model.plants.Images;
import com.agostina.mr.plantagram2.model.plants.Plant;
import com.agostina.mr.plantagram2.model.plants.Suggestions;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class Converters {
        @TypeConverter
        public static ArrayList<Images> fromString(String value) {
            Type listType = new TypeToken<ArrayList<Images>>() {}.getType();
            return new Gson().fromJson(value, listType);
        }

        @TypeConverter
        public static String fromArrayList(ArrayList<Images> list) {
            Gson gson = new Gson();
            String json = gson.toJson(list);
            return json;
        }

    @TypeConverter
    public static LiveData<ArrayList<Plant>> plantFromString(String value) {
        Type listType = new TypeToken<LiveData<ArrayList<Plant>>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String plantFromArrayList(LiveData<ArrayList<Plant>> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @TypeConverter
    public static ArrayList<Suggestions> suggestionsFromString(String value) {
        Type listType = new TypeToken<ArrayList<Suggestions>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String suggestionsFromArrayList(ArrayList<Suggestions> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static User userFromString(String value) {
        return value == null ? null : new User();
    }

    @TypeConverter
    public static String userToString(User user) {
        return user.toString();
    }
    }

