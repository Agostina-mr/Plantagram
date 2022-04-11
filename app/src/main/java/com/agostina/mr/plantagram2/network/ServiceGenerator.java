package com.agostina.mr.plantagram2.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static PlantIdApi plantIdApi;

    public static PlantIdApi getPokemonApi() {
        if (plantIdApi == null) {
            plantIdApi = new Retrofit.Builder()
                    .baseUrl("")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(PlantIdApi.class);
        }
        return plantIdApi;
    }

}
