package com.agostina.mr.plantagram2.ui.garden;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.agostina.mr.plantagram2.repository.PlantFirebaseRepository;
import com.google.firebase.database.Query;

public class GardenViewModel extends AndroidViewModel {
    private PlantFirebaseRepository plantFirebaseRepository;

    public GardenViewModel(@NonNull Application application) {
        super(application);
        plantFirebaseRepository = PlantFirebaseRepository.getInstance();

    }

    public Query getSpecificQuery() {
       return plantFirebaseRepository.getSpecificUserQuery();
    }
}
