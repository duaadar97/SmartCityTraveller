package com.pucit.mcproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pucit.mcproject.Models.City;
import com.pucit.mcproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder>   {


    private ArrayList<City> cityArrayList;
    private LayoutInflater mLayoutInflater;
    public CityAdapter(Context mContext, ArrayList<City> cityArrayList){
        this.cityArrayList=cityArrayList;
        mLayoutInflater=LayoutInflater.from(mContext);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=mLayoutInflater.inflate(R.layout.single_city_data,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        City city=cityArrayList.get(position);
        holder.txt_cityName.setText(city.getName());
        //load Image
        Picasso.get()
                .load(city.getImage())
                .placeholder(R.mipmap.ic_launcher_round)
                .into(holder.img_cityImage);
    }

    @Override
    public int getItemCount() {
        return cityArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img_cityImage;
        TextView txt_cityName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_cityImage=itemView.findViewById(R.id.img_cityImage);
            txt_cityName=itemView.findViewById(R.id.txt_cityName);
        }
    }
}
