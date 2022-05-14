package com.agostina.mr.plantagram2.utilities;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

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
        return data;
    }

    private static String base64EncodeFromFile(String fileString) throws Exception {
        File file = new File(fileString);
        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            String encoded = Base64.getEncoder().encodeToString(fileContent);
            return encoded;
        } catch (IOException e) {
            throw new IllegalStateException("could not read file " + file, e);
        }
    }
    public static String postKeyGenerator(String userName){
        int random = (int) ((Math.random() * (20000 - 1000)) + 1000);
       return userName.concat(String.valueOf(random));
    }
}
