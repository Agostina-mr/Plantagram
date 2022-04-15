package com.agostina.mr.plantagram2.ui.request;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.agostina.mr.plantagram2.model.plants.Plant;
import com.agostina.mr.plantagram2.repository.PlantRepository;

public class CreateRequestViewModel extends ViewModel {
    private PlantRepository plantRepository;

    public CreateRequestViewModel() {
        this.plantRepository = PlantRepository.getInstance();
    }

     public void plantIdentification(Uri photoPath) {
        try {
            plantRepository.plantIdentification(photoPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
LiveData<Plant> getIdentifiedPlant(){
        return plantRepository.getIdentifiedPlant();
}


}
