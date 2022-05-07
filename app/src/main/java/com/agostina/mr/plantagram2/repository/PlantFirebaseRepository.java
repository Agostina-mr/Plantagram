package com.agostina.mr.plantagram2.repository;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.core.os.HandlerCompat;

import com.agostina.mr.plantagram2.model.plants.PlantPost;
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
        myRef = firebaseDatabase.getReference();//.child("users").child(userId);
        plant = new PlantLiveData(myRef);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {
                   /* for (DataSnapshot das : ds.getChildren()) {
                        PlantPost plantPost = das.getValue(PlantPost.class);
                    }*/
                    PlantPost plantPost = ds.getValue(PlantPost.class);
                    assert plantPost != null;
                    System.out.println(plantPost.getAuthorComment());
                }}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myRef.addValueEventListener(valueEventListener);
    }

    public void savePlantFirebase(PlantPost plantPost) {
        myRef.push().setValue(plantPost);
    }

    public Query getQuery(){

        Query query= myRef
                .limitToLast(50).orderByKey();
        return query;
    }




}
