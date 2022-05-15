package com.agostina.mr.plantagram2.ui.garden;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.agostina.mr.plantagram2.model.post.PlantPost;
import com.agostina.mr.plantagram2.repository.FirebaseStorageRepository;
import com.agostina.mr.plantagram2.repository.PlantFirebaseRepository;
import com.google.firebase.database.Query;
import com.google.firebase.storage.StorageReference;

public class GardenViewModel extends AndroidViewModel {
    private PlantFirebaseRepository plantFirebaseRepository;
    private FirebaseStorageRepository storageRepository;

    public GardenViewModel(@NonNull Application application) {
        super(application);
        plantFirebaseRepository = PlantFirebaseRepository.getInstance();
        storageRepository = FirebaseStorageRepository.getInstance();

    }

    public Query getSpecificQuery() {
       return plantFirebaseRepository.getSpecificUserQuery();
    }

    public void updateLikes(PlantPost plantPost) {
        plantFirebaseRepository.updateLikes(plantPost);
    }

    public void openCommentsSection(PlantPost plantPost) {
        plantFirebaseRepository.setSpecificPost(plantPost);
    }

    public StorageReference getUserProfilePath(String key) {
        return storageRepository.getStorageReference(key);
    }
}
