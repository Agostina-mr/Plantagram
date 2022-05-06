package com.agostina.mr.plantagram2.repository;

import com.agostina.mr.plantagram2.model.plants.Plant;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PlantFirebaseRepository {
    private static PlantFirebaseRepository instance;
    private DatabaseReference myRef;
    private PlantLiveData plant ;

    private PlantFirebaseRepository(){}

    public static synchronized PlantFirebaseRepository getInstance() {
        if(instance == null)
            instance = new PlantFirebaseRepository();

        return instance;
    }

    public void init(String userId) {
        myRef = FirebaseDatabase.getInstance("https://plantagram-7693c-default-rtdb.europe-west1.firebasedatabase.app/")
                .getReference().child("users").child(userId);

       plant = new PlantLiveData(myRef);
    }

    public void savePlantFirebase(Plant plant) {
       myRef.setValue(plant);


    }

    public PlantLiveData getPlant() {
       return plant;
    }
}
