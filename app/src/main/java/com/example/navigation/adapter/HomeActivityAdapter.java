package com.example.navigation.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigation.R;
import com.example.navigation.model.PlantInfo;
import com.example.navigation.resrhelper.RestClient;
import com.example.navigation.resrhelper.RetroInterfaceAPI;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //  Glide.with(context).load(judgeData.getImage()).into(holder.imageView);
        holder.temp.setText(plantInfos.get(position).getTemperature());
        holder.humidity.setText(plantInfos.get(position).getHumidity());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RetroInterfaceAPI mInterface = RestClient.getClient();
                Call<ResponseBody> call = mInterface.deleteData(plantInfos.get(position).getId());
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        deleteShowDialog(position);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetroInterfaceAPI mInterfaces = RestClient.getClient();
                Call<ResponseBody> call = mInterfaces.updateData(plantInfos.get(position).getId());
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        updateShowDialog(position);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });
    }

    public void deleteShowDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are you sure want to delete ");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Deleted Successfully !" + position, Toast.LENGTH_SHORT).show();
                deleteData(position);
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void updateShowDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are you sure want to Update ");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Update Functionality !" + position, Toast.LENGTH_SHORT).show();
                updateData(position);

            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public int getItemCount() {
        return plantInfos.size();
    }

    /**
     * REMOVES THE ROWS:
     *
     * @param position
     */
    public void deleteData(int position) {
        plantInfos.remove(position);
        notifyItemRemoved(position);
    }


    /**
     * UPDATE THE ROWS:
     *
     * @param position
     */
    private void updateData(int position) {

        /**
         * Write Business Logic Here @ElishDhungana
         */
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView humidity, temp;
        ImageView imageView, delete, edit;

        public MyViewHolder(View itemView) {
            super(itemView);
            humidity = itemView.findViewById(R.id.home_activity_item_view_humidity);
            temp = itemView.findViewById(R.id.home_activity_item_view_temp);
            imageView = itemView.findViewById(R.id.home_activity_item_view_image);
            delete = itemView.findViewById(R.id.delete);
            edit = itemView.findViewById(R.id.edit);

        }
    }


}