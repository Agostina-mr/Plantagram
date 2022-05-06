package com.agostina.mr.plantagram2.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.agostina.mr.plantagram2.model.plants.Plant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class PlantLiveData extends LiveData<Plant> {
   private DatabaseReference databaseReference;

    public PlantLiveData(DatabaseReference myRef) {
        databaseReference = myRef;
    }

    private final ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            Plant plant = snapshot.getValue(Plant.class);
            setValue(plant);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };

    @Override
    protected void onActive() {
        super.onActive();
        databaseReference.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        databaseReference.removeEventListener(listener);
    }
}