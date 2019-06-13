package com.example.navigation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.navigation.adapter.HomeActivityAdapter;
import com.example.navigation.utils.Utils;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_plant_disease);

        toolbar = findViewById(R.id.custom_toolbar);
        toolbar.setTitle("Plant disease");
        Utils.toolbarConfiguration(toolbar, this);

        recyclerView = findViewById(R.id.recycleview);

        setAdapter();
    }

    public void setAdapter(){
        HomeActivityAdapter homeActivityAdapter = new HomeActivityAdapter(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(homeActivityAdapter);
    }
}
