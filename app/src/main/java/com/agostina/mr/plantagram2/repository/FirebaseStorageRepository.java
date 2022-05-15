package com.agostina.mr.plantagram2.repository;

import android.net.Uri;
import android.util.Log;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class FirebaseStorageRepository {
    private FirebaseStorage storage;
    private static FirebaseStorageRepository instance;
    private StorageReference storageReference;
    private UploadTask uploadTask;
    private String userUid;

    private FirebaseStorageRepository() {
        storage = FirebaseStorage.getInstance("gs://plantagram-7693c.appspot.com");
        storageReference = storage.getReference("images/" );
        this.userUid = userUid;
    }

    public static FirebaseStorageRepository getInstance(){
        if (instance==null){
            instance = new FirebaseStorageRepository();
        }
        return instance;
    }

    public void uploadPicture(Uri path, String userUid){
        UploadTask uploadTask = storageReference.child(userUid).putFile(path);
        uploadTask.addOnFailureListener(
                e -> Log.i("Firebase Storage: ", "Can't upload")).addOnSuccessListener(
                taskSnapshot -> Log.i("Firebase Storage: ", "Upload done"));
    }

    public StorageReference getStorageReference(String userUid) {
        return storageReference.child(userUid+"/");
    }
}
