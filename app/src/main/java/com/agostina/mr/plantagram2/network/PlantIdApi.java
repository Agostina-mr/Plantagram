package com.agostina.mr.plantagram2.network;

import com.agostina.mr.plantagram2.model.plants.ImagesResponse;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface PlantIdApi {
    @POST("identify")
   Call<ImagesResponse> getPlantIdentification(@Header("Api-Key") String api_key, @Body JsonObject data);
}
