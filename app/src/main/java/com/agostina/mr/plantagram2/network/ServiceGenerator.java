package com.agostina.mr.plantagram2.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static final Retrofit.Builder builder = new Retrofit.Builder()
            // .baseUrl("https://api.plant.id/v2/")
            .baseUrl("https://run.mocky.io/v3/8451c720-82e9-4e33-9b0b-c486d6f1e0cc/")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();
    private static final PlantIdApi plantApi = retrofit.create(PlantIdApi.class);
    public static PlantIdApi getPlantApi(){
        return plantApi;
    }
}

