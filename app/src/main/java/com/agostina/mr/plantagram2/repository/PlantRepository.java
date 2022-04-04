package com.agostina.mr.plantagram2.repository;

import com.agostina.mr.plantagram2.R;
import com.agostina.mr.plantagram2.model.PlantPost;

import java.util.ArrayList;

public class PlantRepository {
    private  ArrayList<PlantPost> plants;
    private static PlantRepository instance;
    private UserRepository userRepository;

    private PlantRepository() {
        this.plants = new ArrayList<>();
        this.userRepository = UserRepository.getInstance();
        //DUMMIE DATA
        plants.add(new PlantPost("Nice flower, short living period. Short length","Krukus",R.drawable.p2, "Parks", userRepository.getUsersById(1) ));
        plants.add(new PlantPost("Fruit","Strawberry", R.drawable.p1, "Strawberry fields", userRepository.getUsersById(0)));
  }
  public static PlantRepository getInstance(){
        if (instance == null)
        {
            instance = new PlantRepository();
        }
        return instance;
  }

    public void addPlant(PlantPost plant)
    {
        plants.add(plant);
    }
    public ArrayList<PlantPost> getPlants() {
        return plants;
    }


}
