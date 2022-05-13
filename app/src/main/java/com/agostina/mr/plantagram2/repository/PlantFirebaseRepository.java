package com.agostina.mr.plantagram2.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.agostina.mr.plantagram2.model.post.PlantPost;
import com.agostina.mr.plantagram2.model.post.Comment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PlantFirebaseRepository {
    private static PlantFirebaseRepository instance;
    private DatabaseReference myRef;
    private final FirebaseDatabase firebaseDatabase;
    private String userUid;
    private String userName;
    private final MutableLiveData<PlantPost> specificPost;

    private PlantFirebaseRepository() {
        firebaseDatabase = FirebaseDatabase.getInstance("https://plantagram-7693c-default-rtdb.europe-west1.firebasedatabase.app/");
        specificPost = new MutableLiveData<PlantPost>();
    }

    public static synchronized PlantFirebaseRepository getInstance() {
        if (instance == null)
            instance = new PlantFirebaseRepository();

        return instance;
    }

    public void init(String userUid, String userName) {
        this.userUid = userUid;
        this.userName = userName;
        myRef = firebaseDatabase.getReference();
    }

    public void savePlantFirebase(PlantPost plantPost) {
        plantPost.setAuthorsId(userUid);
        myRef.child("posts").child(userUid).push().setValue(plantPost);
    }

    public Query getAllPostsQuery() {
        //this method is called by the view model, then the view model is called in the fragment to create the adapter
        Query query = myRef.child("posts");
        return query;
    }

    public Query getSpecificUserQuery() { Query query = myRef.child("posts").child(userUid);  return query; }


    public void updateLikes(PlantPost plantPost) {

        myRef.child("posts").child(plantPost.getAuthorsId()).child(plantPost.getPostId()).setValue(plantPost);
    }

    public void setSpecificPost(PlantPost plantPost){
        myRef.child("posts").child(plantPost.getAuthorsId()).child(plantPost.getPostId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                specificPost.setValue(snapshot.getValue(PlantPost.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public LiveData<PlantPost> getOnlySpecificPost(){
        return specificPost;
    }

    public void updateComments(PlantPost plantPost, String comment) {
        Comment newComment = new Comment(userUid,userName, comment);
        plantPost.getComments().add(newComment);
        myRef.child("posts").child(plantPost.getAuthorsId()).child(plantPost.getPostId()).setValue(plantPost);

    }
}
