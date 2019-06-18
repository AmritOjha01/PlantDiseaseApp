package com.example.navigation.resrhelper;

import com.example.navigation.model.PlantInfo;
import com.example.navigation.model.SaveInfo;
import com.example.navigation.model.SaveResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface RetroInterfaceAPI {

    /**
     * GET ALL RECORD
     */
    @GET("getall")
    Call<List<PlantInfo>> getPlantInfo();


    /**
     * SAVE RECORD
     */
    @POST("save")
    Call<SaveInfo> getSaveInfo(@Body SaveResponse saveResponse
    );


    /**
     * DELETE RECORD
     */
    //@DELETE("delete")



    /**
     * UPDATE RECORD
     */
    //@PUT("update")





}
