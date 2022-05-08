package com.agostina.mr.plantagram2.ui.saveplants;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.agostina.mr.plantagram2.model.plants.Plant;
import com.agostina.mr.plantagram2.model.plants.PlantPost;
import com.agostina.mr.plantagram2.repository.PlantFirebaseRepository;
import com.agostina.mr.plantagram2.repository.PlantRepository;

public class SavePlantViewModel extends AndroidViewModel {

    private final PlantRepository plantRepository;
    private final PlantFirebaseRepository plantFirebaseRepository;

    public SavePlantViewModel(@NonNull Application application) {
        super(application);
        plantRepository = PlantRepository.getInstance(application);
       plantFirebaseRepository = PlantFirebaseRepository.getInstance();
    }

    public LiveData<Plant> getIdentifiedPlant() {
    return plantRepository.getIdentifiedPlant();
    }

    public void savePlant(PlantPost plant) {
        plantFirebaseRepository.savePlantFirebase(plant);
    }
}
