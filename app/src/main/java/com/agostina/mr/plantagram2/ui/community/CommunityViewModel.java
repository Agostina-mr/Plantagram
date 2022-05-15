package com.agostina.mr.plantagram2.ui.community;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.agostina.mr.plantagram2.model.post.PlantPost;
import com.agostina.mr.plantagram2.repository.FirebaseStorageRepository;
import com.agostina.mr.plantagram2.repository.PlantFirebaseRepository;
import com.agostina.mr.plantagram2.repository.UserRepository;
import com.google.firebase.database.Query;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class CommunityViewModel extends AndroidViewModel {

    private PlantFirebaseRepository plantFirebaseRepository;
    private UserRepository userRepository;
    private MutableLiveData<PlantPost> singlePlantPost;
    private FirebaseStorageRepository storageRepository;

    public CommunityViewModel(@NonNull Application application) {
        super(application);
        plantFirebaseRepository = PlantFirebaseRepository.getInstance();
        userRepository = UserRepository.getInstance(application);
        singlePlantPost = new MutableLiveData<PlantPost>();
        storageRepository = FirebaseStorageRepository.getInstance();

    }

    public Query getAllPostsQuery()
    {
       return plantFirebaseRepository.getAllPostsQuery();
    }


    public void updatePlantPost(PlantPost plantPost) {
        plantFirebaseRepository.updateLikes(plantPost);
    }

    public String getCurrentUser() {
        String userUid="";
        if (userRepository.getCurrentUser() != null){
            userUid= userRepository.getCurrentUser().getValue().getUid();
        }
       return userUid;
    }

    public void updateLikes(PlantPost plantPost) {
        List<String> updatedLikes = plantPost.getLikes();
        if (!plantPost.doesUserLikeIt(plantPost.getViewBy())){
            updatedLikes.add(getCurrentUser());
            plantPost.setLikes(updatedLikes);
            updatePlantPost(plantPost);
        }
        else{
            updatedLikes.remove(getCurrentUser());
            plantPost.setLikes(updatedLikes);
            updatePlantPost(plantPost);
        }
    }

    public void openCommentsSection(PlantPost plantPost) {
        plantFirebaseRepository.setSpecificPost(plantPost);
        }

    public LiveData<PlantPost> getSinglePlantPost() {
        return plantFirebaseRepository.getOnlySpecificPost();
    }

    public void addComment(PlantPost plantPost, String comment) {
       plantFirebaseRepository.updateComments(plantPost, comment);

    }
    public StorageReference getUserProfilePath(String userUid){
        return storageRepository.getStorageReference(userUid);

    }
}
