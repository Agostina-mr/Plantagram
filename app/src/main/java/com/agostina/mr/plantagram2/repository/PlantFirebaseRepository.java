package com.agostina.mr.plantagram2.repository;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.core.os.HandlerCompat;

import com.agostina.mr.plantagram2.model.plants.Plant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PlantFirebaseRepository {
    private static PlantFirebaseRepository instance;
    private DatabaseReference myRef;
    private PlantLiveData plant;
    private final FirebaseDatabase firebaseDatabase;
    private final Handler mainThreadHandler;

    private PlantFirebaseRepository() {
        firebaseDatabase = FirebaseDatabase.getInstance("https://plantagram-7693c-default-rtdb.europe-west1.firebasedatabase.app/");
    mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());
    }

    public static synchronized PlantFirebaseRepository getInstance() {
        if (instance == null)
            instance = new PlantFirebaseRepository();

        return instance;
    }

    public void init(String userId) {
        myRef = firebaseDatabase.getReference().child("users").child(userId);
        plant = new PlantLiveData(myRef);
        //myRef = firebaseDatabase.getReference().child("plants");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Plant plant = snapshot.getValue(Plant.class);
             /*   for(DataSnapshot ds : snapshot.getChildren()) {
                    for (DataSnapshot dSnapshot : ds.getChildren()) {
                        Plant plant1 = dSnapshot.getValue(Plant.class);

                    }
                }*/}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myRef.addValueEventListener(valueEventListener);
    }

    public void savePlantFirebase(Plant plant) {
        myRef.push().child("plants").setValue(plant);

    }

    public PlantLiveData getPlant() {
        return plant;
    }



    public Query getAllPlants(){
        return/* firebaseDatabase
                .getReference()
                    .child("plants")
                .limitToLast(50);*/myRef.child("plants").limitToLast(10);
    }




}
