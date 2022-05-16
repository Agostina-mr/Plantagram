package com.agostina.mr.plantagram2.network;

import com.agostina.mr.plantagram2.model.responses.PlantResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface PlantIdApi {

  ///mocki @GET("hello")
   @POST("identify")
   Call<PlantResponse> getPlantIdentification(@Header("Api-Key") String api_key, @Body JsonObject data);
}
