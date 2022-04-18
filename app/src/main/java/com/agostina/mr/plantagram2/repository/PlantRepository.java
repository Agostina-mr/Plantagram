package com.agostina.mr.plantagram2.repository;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.agostina.mr.plantagram2.model.plants.Images;
import com.agostina.mr.plantagram2.model.plants.Plant;
import com.agostina.mr.plantagram2.model.plants.responses.PlantResponse;
import com.agostina.mr.plantagram2.network.PlantIdApi;
import com.agostina.mr.plantagram2.network.ServiceGenerator;
import com.agostina.mr.plantagram2.utilities.Helper;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class PlantRepository {
    private ArrayList<Plant> plants;
    private static PlantRepository instance;
    private final MutableLiveData<Plant> identifiedPlant;



    private PlantRepository() {
        identifiedPlant = new MutableLiveData<Plant>();
        this.plants = new ArrayList<>();

    }

    public LiveData<Plant> getIdentifiedPlant() {
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
    public void requestIdentification(String photoPath) throws Exception {
        PlantIdApi plantIdApi = ServiceGenerator.getPlantApi();
        String apiKey = "DUTfIkBzHladcugpM3x1b7wqGM7foXH9BxTTOIyvqAr1Rs2M0P";
        JsonObject data = Helper.formatData(photoPath);
        System.out.println(data);
        Call<PlantResponse> plantResponseCall = plantIdApi.getPlantIdentification(/*apiKey, data*/);
         plantResponseCall.enqueue(new Callback<PlantResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(@NonNull Call<PlantResponse> call, Response<PlantResponse> response) {

                    if (response.isSuccessful())
                    {
                        System.out.println("------------"+response.body().getPlant().getSuggestions().get(0).getPlant_name());
                        assert response.body() != null;
                        identifiedPlant.setValue(response.body().getPlant());
                        System.out.println("+++" + identifiedPlant.getValue().getImages().get(0).getUrl());
                    }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<PlantResponse> call, Throwable t) {
            }
        });
    }


}
