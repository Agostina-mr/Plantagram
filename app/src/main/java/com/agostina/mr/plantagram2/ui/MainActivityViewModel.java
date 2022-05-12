package com.agostina.mr.plantagram2.ui;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.agostina.mr.plantagram2.repository.PlantFirebaseRepository;
import com.agostina.mr.plantagram2.repository.UserRepository;
import com.google.firebase.auth.FirebaseUser;

public class MainActivityViewModel extends AndroidViewModel {
    private final UserRepository userRepository;
    private final PlantFirebaseRepository plantFirebaseRepository;


    public MainActivityViewModel(Application app){
        super(app);
        userRepository = UserRepository.getInstance(app);
        plantFirebaseRepository = PlantFirebaseRepository.getInstance();

    }

    public void init() {
        String userUid = userRepository.getCurrentUser().getValue().getUid();
        String userName = userRepository.getCurrentUser().getValue().getDisplayName();
        plantFirebaseRepository.init(userUid, userName);
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }

    public void signOut() {
        userRepository.signOut();
    }
}