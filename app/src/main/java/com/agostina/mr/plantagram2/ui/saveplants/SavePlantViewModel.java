package com.agostina.mr.plantagram2.ui.saveplants;

import android.provider.MediaStore;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.agostina.mr.plantagram2.model.plants.Images;
import com.agostina.mr.plantagram2.model.plants.Plant;
import com.agostina.mr.plantagram2.repository.PlantRepository;

import java.util.ArrayList;

public class SavePlantViewModel extends ViewModel {

    private PlantRepository plantRepository;


    public SavePlantViewModel() {
        this.plantRepository = PlantRepository.getInstance();
    }

    public LiveData<Plant> getIdentifiedPlant() {
    return plantRepository.getIdentifiedPlant();
    }


}
