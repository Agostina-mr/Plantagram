package com.agostina.mr.plantagram2.repository;

import android.content.Context;


import android.os.StrictMode;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.agostina.mr.plantagram2.model.Images;
import com.agostina.mr.plantagram2.model.ImagesResponse;
import com.agostina.mr.plantagram2.model.Plant;
import com.agostina.mr.plantagram2.model.PlantPost;
import com.agostina.mr.plantagram2.model.PlantResponse;
import com.agostina.mr.plantagram2.network.PlantIdApi;
import com.agostina.mr.plantagram2.network.ServiceGenerator;



import org.json.JSONObject;


import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;


import org.json.JSONArray;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;
import retrofit2.internal.EverythingIsNonNull;

public class PlantRepository {
    private ArrayList<PlantPost> plantPosts;
    private static PlantRepository instance;
    private UserRepository userRepository;
    private final MutableLiveData<Plant> identifiedPlant;


    private PlantRepository() {
        identifiedPlant = new MutableLiveData<>();
        this.plantPosts = new ArrayList<>();
        this.userRepository = UserRepository.getInstance();

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder(StrictMode.getVmPolicy())
                .detectLeakedClosableObjects()
                .build());

    }

    public static synchronized PlantRepository getInstance() {
        if (instance == null) {
            instance = new PlantRepository();
        }
        return instance;
    }

    public void addPlant(Plant plant) {
        plantPosts.add(new PlantPost(plant, userRepository.getUsersById(1)));
    }

    public ArrayList<PlantPost> getPlantPosts() {
        return plantPosts;
    }



    public void plantIdentification(String photoPath) throws Exception {
        //access Retrofit service generator
        PlantIdApi plantIdApi = ServiceGenerator.getPlantApi();
        //My api key
        String apiKey = "DUTfIkBzHladcugpM3x1b7wqGM7foXH9BxTTOIyvqAr1Rs2M0P";
        // get image from local file
        String[] flowers = new String[]{photoPath};

        JSONObject data = new JSONObject();
       // data.put("id", null);
        JSONArray images = new JSONArray();
        for (String filename : flowers) {
            String fileData = base64EncodeFromFile(filename);
            images.put(fileData);
        }
        data.put("images", images);
        System.out.println(data);

        /*Call<ImagesResponse> plantResponseCall = plantIdApi.getPlantIdentification(apiKey, data);
         plantResponseCall.enqueue(new Callback<ImagesResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(@NonNull Call<ImagesResponse> call, Response<ImagesResponse> response) {
                try {
                    System.out.println("On response code: " + response.code() + response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<ImagesResponse> call, Throwable t) {
                System.out.println("--------Onfailure---------" + t.getMessage());
            }
        });*/
    }
    private static String base64EncodeFromFile(String fileString) throws Exception {
        File file = new File(fileString);
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        DataInputStream dataInputStream = new DataInputStream(fis);
        dataInputStream.readFully(bytes);
        String res = Base64.getEncoder().encodeToString(bytes);
        fis.close();
        return res;
    }
    public LiveData<Plant> getIdentifiedPlant() {
        return identifiedPlant;
    }
}
