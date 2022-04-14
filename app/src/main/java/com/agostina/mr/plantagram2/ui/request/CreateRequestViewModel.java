package com.agostina.mr.plantagram2.ui.request;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.agostina.mr.plantagram2.MainActivity;
import com.agostina.mr.plantagram2.model.Plant;
import com.agostina.mr.plantagram2.model.PlantResponse;
import com.agostina.mr.plantagram2.repository.PlantRepository;

import java.util.ArrayList;

public class CreateRequestViewModel extends ViewModel {
    private PlantRepository plantRepository;




    public CreateRequestViewModel() {
        this.plantRepository = PlantRepository.getInstance();
    }

     public void plantIdentification(Context context, String photoPath) {
        try {
            plantRepository.plantIdentification(context, photoPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
LiveData<Plant> getIdentifiedPlant(){
        return plantRepository.getIdentifiedPlant();
}


}
