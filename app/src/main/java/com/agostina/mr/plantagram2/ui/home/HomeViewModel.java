package com.agostina.mr.plantagram2.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.agostina.mr.plantagram2.model.plants.Plants;
import com.agostina.mr.plantagram2.repository.PlantFirebaseRepository;
import com.agostina.mr.plantagram2.repository.PlantRepository;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {


    private MutableLiveData<List<Plants>>  plantList;
    private PlantRepository plantRepository;
    private PlantFirebaseRepository plantFirebaseRepository;


    public HomeViewModel(@NonNull Application application) {
        super(application);
        plantRepository = PlantRepository.getInstance(application);
        plantList = new MutableLiveData<>();
        plantFirebaseRepository = PlantFirebaseRepository.getInstance();
        plantList.setValue(new ArrayList<>());

    }

    public LiveData<ArrayList<Plants>> getAllPlants()
    {
      return plantFirebaseRepository.getPlantsMutableDataList();
    }

    public Query getQuery()
    {
       return plantFirebaseRepository.getQuery();
    }


}