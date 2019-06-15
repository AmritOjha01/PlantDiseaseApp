package com.example.navigation.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.navigation.R;
import com.example.navigation.model.PlantInfo;

import java.util.ArrayList;
import java.util.List;


public class HomeActivityAdapter extends RecyclerView.Adapter<HomeActivityAdapter.MyViewHolder> {

    Context context;
   private List<PlantInfo> plantInfos;
    Activity activity;

    public HomeActivityAdapter(Context context, List<PlantInfo> plantInfos, Activity activity) {
        this.context = context;
        this.plantInfos = plantInfos;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_activity_item_view, parent, false);
        context = parent.getContext();
        return new HomeActivityAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        holder.temp.setText(plantInfos.get(position).getTemperature());
        holder.humidity.setText(plantInfos.get(position).getHumidity());



      //  Glide.with(context).load(judgeData.getImage()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return plantInfos.size();
    }





    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView humidity, temp;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            humidity = itemView.findViewById(R.id.home_activity_item_view_humidity);
            temp = itemView.findViewById(R.id.home_activity_item_view_temp);
            imageView = itemView.findViewById(R.id.home_activity_item_view_image);
        }
    }
}