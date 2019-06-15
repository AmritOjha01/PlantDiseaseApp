package com.example.navigation.resrhelper;

import com.example.navigation.model.PlantInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetroInterfaceAPI {

    @GET("lqkdx") //about-show
    Call<List<PlantInfo>> getPlantInfo();
}
