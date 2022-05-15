package com.agostina.mr.plantagram2.utilities;

import com.agostina.mr.plantagram2.model.plants.Plant;
import com.agostina.mr.plantagram2.model.plants.Suggestions;
import com.agostina.mr.plantagram2.model.post.PlantPost;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

public class Helper {

    public static JsonObject formatData(String photoPath) throws Exception {
        // get image from local file
        String[] flowers = new String[]{String.valueOf(photoPath)};
        JsonObject data = new JsonObject();
        // data.put("id", null);
        JsonArray images = new JsonArray();
        for (String filename : flowers) {
            String fileData = base64EncodeFromFile(filename);
            images.add(fileData);
        }
        data.add("images", images);
        JsonArray plantDetails = new JsonArray();
        plantDetails.add("common_names");
        plantDetails.add("url");
        plantDetails.add("name_authority");
        plantDetails.add("wiki_description");
        data.add("plant_details", plantDetails);
        return data;
    }

    public static String base64EncodeFromFile(String fileString) throws Exception {
        File file = new File(fileString);
        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            String encoded = Base64.getEncoder().encodeToString(fileContent);
            return encoded;
        } catch (IOException e) {
            throw new IllegalStateException("could not read file " + file, e);
        }
    }

    public static PlantPost toPlantPost(Plant plant) {
        PlantPost plantPost = new PlantPost();
        if (plant != null) {
            String pictureToAdd = "";
            String nameToAdd = " | ";
            if (FirebaseAuth.getInstance().getCurrentUser().getDisplayName() != null) {
                plantPost.setUserName(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
            }
            if (FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl() != null) {
                plantPost.setUserPicture(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl()).toString());
            }
            pictureToAdd = plant.getImages().get(0).getUrl();
            plantPost.setPicture(pictureToAdd);
            if (plant.getSuggestions() != null) {
                for (Suggestions suggestions : plant.getSuggestions()
                ) {
                    nameToAdd = (nameToAdd.concat(suggestions.getPlant_name()).concat(" | "));
                }
                plantPost.setPlantName(nameToAdd);
            }

            if (plant.getSuggestions().get(0).getPlant_details().getWiki_description().getValue() != null) {
                plantPost.setPlantDescription(plant.getSuggestions().get(0).getPlant_details().getWiki_description().getValue());
            }

        }
        return plantPost;

    }

    public static String postKeyGenerator(String userName) {
        int random = (int) ((Math.random() * (20000 - 1000)) + 1000);
        return userName.concat(String.valueOf(random));
    }

    public static long getTimeStamp(){
        return new Date().getTime();

    }

}
