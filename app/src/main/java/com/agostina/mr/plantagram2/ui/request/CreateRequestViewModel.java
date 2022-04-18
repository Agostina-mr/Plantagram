package com.agostina.mr.plantagram2.ui.request;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.agostina.mr.plantagram2.model.plants.Plant;
import com.agostina.mr.plantagram2.model.plants.responses.PlantResponse;
import com.agostina.mr.plantagram2.repository.PlantRepository;

public class CreateRequestViewModel extends AndroidViewModel {
    private PlantRepository plantRepository;

    public CreateRequestViewModel(@NonNull Application application) {
        super(application);
        plantRepository = PlantRepository.getInstance();
    }


    public void plantIdentification(String photoPath) {
        try {
            plantRepository.requestIdentification(photoPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
