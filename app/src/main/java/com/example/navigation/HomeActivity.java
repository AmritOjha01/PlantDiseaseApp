package com.example.navigation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.navigation.adapter.HomeActivityAdapter;
import com.example.navigation.model.PlantInfo;
import com.example.navigation.resrhelper.RestClient;
import com.example.navigation.resrhelper.RetroInterfaceAPI;
import com.example.navigation.utils.Utils;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private List<PlantInfo> plantList = null;
    ProgressBar progressbar;
    PlantInfo pl;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_plant_disease);
        progressbar = findViewById(R.id.progressbar);
        progressbar.bringToFront();

        toolbar = findViewById(R.id.custom_toolbar);
        toolbar.setTitle("Plant disease");
        Utils.toolbarConfiguration(toolbar, this);

        recyclerView = findViewById(R.id.recycleview);
        callRetrofitShowData();
    }

    private void callRetrofitShowData() {
        progressbar.setVisibility(View.VISIBLE);

        RetroInterfaceAPI mInterface = RestClient.getClient();
        Call<List<PlantInfo>> call = mInterface.getPlantInfo();
        call.enqueue(new Callback<List<PlantInfo>>() {
            @Override
            public void onResponse(Call<List<PlantInfo>> call, Response<List<PlantInfo>> response) {
                if (response.body() != null) {
                    if (response.code() == 200) {
                        progressbar.setVisibility(View.GONE);
                        Log.d("getdata", "dataplant");
                        plantList = response.body();
                        HomeActivityAdapter homeActivityAdapter = new HomeActivityAdapter(getApplicationContext(), plantList, HomeActivity.this);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.setAdapter(homeActivityAdapter);

                    } else {
                        progressbar.setVisibility(View.GONE);

                    }
                }
            }

            @Override
            public void onFailure(Call<List<PlantInfo>> call, Throwable t) {
                progressbar.setVisibility(View.GONE);

            }
        });
    }

}
