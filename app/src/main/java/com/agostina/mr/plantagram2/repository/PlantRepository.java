package com.agostina.mr.plantagram2.repository;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.agostina.mr.plantagram2.model.plants.Plant;
import com.agostina.mr.plantagram2.model.plants.responses.PlantResponse;
import com.agostina.mr.plantagram2.network.PlantIdApi;
import com.agostina.mr.plantagram2.network.ServiceGenerator;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class PlantRepository {
    private ArrayList<Plant> plants;
    private static PlantRepository instance;
    private UserRepository userRepository;
    private final MutableLiveData<PlantResponse> identifiedPlant;


    private PlantRepository() {
        identifiedPlant = new MutableLiveData<>();
        this.plants = new ArrayList<>();
        this.userRepository = UserRepository.getInstance();

    }

    public LiveData<PlantResponse> getIdentifiedPlant() {
        return identifiedPlant;
    }

    public static synchronized PlantRepository getInstance() {
        if (instance == null) {
            instance = new PlantRepository();
        }
        return instance;
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public ArrayList<Plant> getPlants() {
        return plants;
    }
    public void plantIdentification(String photoPath) throws Exception {
        //access Retrofit service generator
        PlantIdApi plantIdApi = ServiceGenerator.getPlantApi();
        //My api key
       // String apiKey = "DUTfIkBzHladcugpM3x1b7wqGM7foXH9BxTTOIyvqAr1Rs2M0P";
        String apiKey = "123";
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
        System.out.println(data);

        Call<PlantResponse> plantResponseCall = plantIdApi.getPlantIdentification(/*apiKey, data*/);
         plantResponseCall.enqueue(new Callback<PlantResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(@NonNull Call<PlantResponse> call, Response<PlantResponse> response) {
                    System.out.println("On response code: " + response.code() + response.body().getPlant().getSuggestions().get(0).getPlant_name());
                    if (response.isSuccessful())
                    {
                            identifiedPlant.setValue(response.body());
                        System.out.println(identifiedPlant.getValue());
                    }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<PlantResponse> call, Throwable t) {
                System.out.println("--------Onfailure---------" + t.getMessage());
            }
        });
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
}
