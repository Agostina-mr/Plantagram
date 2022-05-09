package com.agostina.mr.plantagram2.ui.community;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.agostina.mr.plantagram2.model.plants.PlantPost;
import com.agostina.mr.plantagram2.repository.PlantFirebaseRepository;
import com.google.firebase.database.Query;

public class CommunityViewModel extends AndroidViewModel {

    private PlantFirebaseRepository plantFirebaseRepository;

    public CommunityViewModel(@NonNull Application application) {
        super(application);
        plantFirebaseRepository = PlantFirebaseRepository.getInstance();
    }

    public Query getAllPostsQuery()
    {
       return plantFirebaseRepository.getAllPostsQuery();
    }


    public void updatePlantPost(PlantPost plantPost) {
    }
}