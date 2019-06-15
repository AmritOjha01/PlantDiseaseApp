package com.example.navigation.resrhelper;

import com.example.navigation.model.PlantInfo;
import com.example.navigation.model.SaveInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface RetroInterfaceAPI {

    @GET("lqkdx") //aboutplant
    Call<List<PlantInfo>> getPlantInfo();

    @GET("lqkdx") //savedata
    Call<SaveInfo> getSaveInfo(@Field("label") String label,
                               @Field("stage_of_infection") String stage,
                               @Field("part_of_the_plant") String part,
                               @Field("if_leaf") String leaf,
                               @Field("farm") String farm,
                               @Field("location") String location,
                               @Field("image") String image,
                               @Field("comments") String comments,
                               @Field("temperature") String temperature,
                               @Field("humdity") String humidity,
                               @Field("rate_of_occurence") String rate,
                               @Field("date") String date
                               );
}
