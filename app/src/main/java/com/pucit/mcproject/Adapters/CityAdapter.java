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
//to load image i used picasso here
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder>   {

    private OnCityClickListener onCityClickListener;
    private ArrayList<City> cityArrayList;
    private LayoutInflater mLayoutInflater;
    public CityAdapter(Context mContext, ArrayList<City> cityArrayList,OnCityClickListener onCityClickListener){
        this.cityArrayList=cityArrayList;
        mLayoutInflater=LayoutInflater.from(mContext);
        this.onCityClickListener=onCityClickListener;
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
        holder.txt_cityName.setText(city.getCityName());
        holder.txt_cityAddress.setText(city.getCityAddress());
        //load Image
        Picasso.get()
                .load(city.getCityImage())
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
        TextView txt_cityAddress;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_cityImage=itemView.findViewById(R.id.img_cityImage);
            txt_cityName=itemView.findViewById(R.id.txt_cityName);
            txt_cityAddress=itemView.findViewById(R.id.txt_cityAddress);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onCityClickListener.onClickCityInRecycler(getAdapterPosition());
                }
            });
        }
    }
    //it execute when we click on a city view
    public interface OnCityClickListener{
        void onClickCityInRecycler(int position);
    }
}
