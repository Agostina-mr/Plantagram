package com.agostina.mr.plantagram2.network;

import android.webkit.WebSettings;

import androidx.annotation.NonNull;

import com.agostina.mr.plantagram2.MainActivity;

import java.io.IOException;

import io.reactivex.plugins.RxJavaPlugins;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
      private static PlantIdApi plantIdApi = null;
      private static Converter.Factory gsonConvertorF = GsonConverterFactory.create();
      private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();



      public static PlantIdApi getPlantIdApi() {
          if (plantIdApi == null) {
              Retrofit retrofit = new Retrofit.Builder().client(getOkHttpClient()).baseUrl("https://api.plant.id/v2/")
                      .addConverterFactory(gsonConvertorF).addCallAdapterFactory(rxJavaCallAdapterFactory).build();
              plantIdApi = retrofit.create(PlantIdApi.class);


          }
          return plantIdApi;

    /*private static final OkHttpClient.Builder client = new OkHttpClient.Builder();
    private static final Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://api.plant.id/v2/")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();
    private static final PlantIdApi plantApi = retrofit.create(PlantIdApi.class);
    public static PlantIdApi getPlantApi(){
        return plantApi;
    }*/

/*

    public static <S> S createService(Class<S> serviceClass){
        return createService(serviceClass, null);
    }
    public static <S> S createService(Class<S> serviceClass, final String authToken){
        if (!TextUtils.isEmpty(authToken)){
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(authToken);
            if (!client.interceptors().contains(interceptor))
            {
                client.addInterceptor(interceptor);
                builder.client(client.build());
                retrofit = builder.build();
            }
        }
        return retrofit.create(serviceClass);
    }
*/

}
private static OkHttpClient getOkHttpClient()
{
    OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        @NonNull
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request().newBuilder().removeHeader("User-Agent").build();
            return chain.proceed(request);
        }
    }).build();
    return httpClient;
}
}
