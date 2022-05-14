package com.agostina.mr.plantagram2.repository;

import android.app.Application;
import android.net.Uri;

import androidx.lifecycle.LiveData;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class UserRepository {

    private final UserLiveData currentUser;
    private final Application app;
    private static UserRepository instance;
    private StorageReference storageReference;

    private UserRepository(Application app) {
        this.app = app;
        currentUser = new UserLiveData();
        storageReference = FirebaseStorage.getInstance().getReference();
    }

    public static synchronized UserRepository getInstance(Application app) {
        if(instance == null)
            instance = new UserRepository(app);
        return instance;
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return currentUser;
    }


    public void signOut() {
        AuthUI.getInstance()
                .signOut(app.getApplicationContext());
    }

    public void setCurrentUser(FirebaseUser user) {
        //currentUser = user;
    }
    public void changeProfile(Uri uri, String userName){

        UserProfileChangeRequest.Builder builder = new UserProfileChangeRequest.Builder();
        builder.setPhotoUri(uri);
        builder.setDisplayName(userName);
        currentUser.getValue().updateProfile(builder.build());

    }

}
