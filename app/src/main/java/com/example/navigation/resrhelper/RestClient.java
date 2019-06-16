package com.example.navigation.resrhelper;

import android.util.Log;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class  RestClient {
    private static RetroInterfaceAPI retroInterfaceAPI;
    private static final String URL="http://114.70.194.120:8000/api/plant/disease/";

    public static RetroInterfaceAPI getClient() {

        if (retroInterfaceAPI == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.d("Retrofit", message);
                }
            });

            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okClient = new OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(2, TimeUnit.MINUTES)
                    .build();

            Retrofit client = new Retrofit.Builder()
                    .client(okClient)
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            retroInterfaceAPI = client.create(RetroInterfaceAPI.class);
        }

        return retroInterfaceAPI;
    }

}

