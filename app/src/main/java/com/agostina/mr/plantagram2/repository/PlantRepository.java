package com.agostina.mr.plantagram2.repository;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.agostina.mr.plantagram2.model.plants.Plant;
import com.agostina.mr.plantagram2.model.plants.responses.PlantResponse;
import com.agostina.mr.plantagram2.network.PlantIdApi;
import com.agostina.mr.plantagram2.network.ServiceGenerator;
import com.agostina.mr.plantagram2.room.PlantDao;
import com.agostina.mr.plantagram2.room.PlantDatabase;
import com.agostina.mr.plantagram2.room.UserDao;
import com.agostina.mr.plantagram2.utilities.Helper;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class PlantRepository {

    private static PlantRepository instance;
    //api
    private final MutableLiveData<Plant> identifiedPlant;
    //room
    private MutableLiveData<ArrayList<Plant>> allPlants;
    private PlantDao plantDao;
    private UserDao userDao;
    private ExecutorService executorService;

    private PlantRepository(Application application) {
        PlantDatabase database = PlantDatabase.getInstance(application);
        plantDao = database.plantDao();
        userDao = database.userDao();
        executorService = Executors.newFixedThreadPool(2);
        identifiedPlant = new MutableLiveData<Plant>();
    }

    public static synchronized PlantRepository getInstance(Application application) {
        if (instance == null) {
            instance = new PlantRepository(application);
        }
        return instance;
    }

    public LiveData<List<Plant>> getAllPlants() {
        return plantDao.getAllPlants();
    }

    public void insert(Plant plants) {
        executorService.execute(() -> plantDao.insertPlant(plants));
    }

    //api related
    public void requestIdentification(String photoPath) throws Exception {
        PlantIdApi plantIdApi = ServiceGenerator.getPlantApi();

        JsonObject data = Helper.formatData(photoPath);
        Call<PlantResponse> plantResponseCall = plantIdApi.getPlantIdentification(/*apiKey, data*/);
        plantResponseCall.enqueue(new Callback<PlantResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(@NonNull Call<PlantResponse> call, Response<PlantResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        identifiedPlant.setValue(response.body().getPlant());

                    }
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<PlantResponse> call, Throwable t) {
            }
        });
    }

    public LiveData<Plant> getIdentifiedPlant() {
        return identifiedPlant;
    }


}
