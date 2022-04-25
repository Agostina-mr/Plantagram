package com.agostina.mr.plantagram2.ui.saveplants;

import android.app.Application;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.agostina.mr.plantagram2.model.plants.Images;
import com.agostina.mr.plantagram2.model.plants.Plant;
import com.agostina.mr.plantagram2.repository.PlantRepository;

import java.util.ArrayList;

public class SavePlantViewModel extends AndroidViewModel {

    private final PlantRepository plantRepository;

    public SavePlantViewModel(@NonNull Application application) {
        super(application);
        plantRepository = PlantRepository.getInstance(application);
    }

    public LiveData<Plant> getIdentifiedPlant() {
    return plantRepository.getIdentifiedPlant();
    }

    public void savePlant(Plant plant) {
        plantRepository.insert(plant);
    }
}
