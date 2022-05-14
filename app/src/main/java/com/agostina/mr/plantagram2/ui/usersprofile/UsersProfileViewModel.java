package com.agostina.mr.plantagram2.ui.usersprofile;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.agostina.mr.plantagram2.repository.UserRepository;
import com.google.firebase.auth.FirebaseUser;

public class UsersProfileViewModel extends AndroidViewModel {
    private UserRepository userRepository;

    public UsersProfileViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance(application);

    }

    public LiveData<FirebaseUser> getCurrentUser(){
       return userRepository.getCurrentUser();

    }
    public void setProfilePicture(Uri uri, String userName){

        userRepository.changeProfile(uri, userName);
    }


}
