package com.agostina.mr.plantagram2.repository;

import com.agostina.mr.plantagram2.model.plants.PlantPost;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class PlantFirebaseRepository {
    private static PlantFirebaseRepository instance;
    private DatabaseReference myRef;
    private final FirebaseDatabase firebaseDatabase;
    private String userUid;

    private PlantFirebaseRepository() {
        firebaseDatabase = FirebaseDatabase.getInstance("https://plantagram-7693c-default-rtdb.europe-west1.firebasedatabase.app/");
    }

    public static synchronized PlantFirebaseRepository getInstance() {
        if (instance == null)
            instance = new PlantFirebaseRepository();

        return instance;
    }

    public void init(String userUid) {
        this.userUid = userUid;
        myRef = firebaseDatabase.getReference();
    }

    public void savePlantFirebase(PlantPost plantPost) {
        myRef.child("posts").child(userUid).push().setValue(plantPost);
    }

    public Query getAllPostsQuery() {
        //this method is called by the view model, then the view model is called in the fragment to create the adapter
        Query query = myRef.child("posts").orderByKey();
        return query;
    }

    public Query getSpecificUserQuery() { Query query = myRef.child("posts").child(userUid);  return query; }


}
