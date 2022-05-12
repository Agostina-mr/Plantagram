package com.agostina.mr.plantagram2.network;

import com.agostina.mr.plantagram2.model.responses.PlantResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlantIdApi {
   // @POST("identify")
    //mocky version

@GET("hello")
   Call<PlantResponse> getPlantIdentification(/*@Header("Api-Key") String api_key, @Body JsonObject data*/);
}
