package com.agostina.mr.plantagram2.network;

import com.agostina.mr.plantagram2.model.ImagesResponse;
import com.agostina.mr.plantagram2.model.PlantResponse;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PlantIdApi {
   // @Headers("content-Type : application/json" +
         //   "api_key : DUTfIkBzHladcugpM3x1b7wqGM7foXH9BxTTOIyvqAr1Rs2M0P")

    @Multipart
    @POST("identify")
    @Headers({
            "Accept:application/json",
            "Content-Type:multipart/form-data"
            , "Api-Key:DUTfIkBzHladcugpM3x1b7wqGM7foXH9BxTTOIyvqAr1Rs2M0P"
    })
  Call<PlantResponse> getPlantIdentification(@Part("images")RequestBody images);
  //  Call<PlantResponse> getPlantIdentification();



}
