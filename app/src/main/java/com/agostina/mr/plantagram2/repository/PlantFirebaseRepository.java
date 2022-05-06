package com.agostina.mr.plantagram2.repository;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.core.os.HandlerCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.agostina.mr.plantagram2.model.plants.Plants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PlantFirebaseRepository {
    private static PlantFirebaseRepository instance;
    private DatabaseReference myRef;
    private PlantLiveData plant;
    private final FirebaseDatabase firebaseDatabase;
    private final Handler mainThreadHandler;
    private ArrayList<Plants> plantsArrayList = new ArrayList<>();
    private MutableLiveData<ArrayList<Plants>> plantsMutableDataList = new MutableLiveData<>();


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
    }


    public void savePlantFirebase(Plants plants) {
        myRef.push().child("plant").setValue(plants);
    }

    public PlantLiveData getPlant() {
        return plant;
    }

    public LiveData<ArrayList<Plants>> getPlantsMutableDataList() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    for (DataSnapshot das : ds.getChildren()) {
                        Plants plants = das.getValue(Plants.class);

                        plantsArrayList.add(plants);
                    }
                }
                plantsMutableDataList.postValue(plantsArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return plantsMutableDataList;
    }

    public Query getAllPlants() {
        return/* firebaseDatabase
                .getReference()
                    .child("plants")
                .limitToLast(50);*/myRef.child("plant").limitToLast(10);
    }




}
