package com.agostina.mr.plantagram2.network;

import android.telecom.Call;

import com.agostina.mr.plantagram2.model.PlantResponse;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PlantIdApi {
    @POST("")
    Call<PlantResponse> getPlantIdentification(@Path("path") String path);


}
