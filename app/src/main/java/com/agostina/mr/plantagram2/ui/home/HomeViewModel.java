package com.agostina.mr.plantagram2.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.agostina.mr.plantagram2.model.plants.PlantPost;
import com.agostina.mr.plantagram2.repository.PlantRepository;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {


    private MutableLiveData<List<PlantPost>>  plantList;
    private PlantRepository plantRepository;


    public HomeViewModel() {
        plantRepository = PlantRepository.getInstance();
        plantList = new MutableLiveData<>();
        plantList.setValue(new ArrayList<>());

    }

    public ArrayList<PlantPost> getPlants()
    {
      return plantRepository.getPlantPosts();
    }

}