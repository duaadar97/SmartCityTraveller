package com.pucit.mcproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pucit.mcproject.Models.Places;
import com.pucit.mcproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<Places> popularPlaces;
    private OnPlacesClickListener listener;

    public PlacesAdapter(Context mContext, ArrayList<Places> popularPlaces, OnPlacesClickListener listener) {
        this.mContext = mContext;
        this.popularPlaces = popularPlaces;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.single_place_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(popularPlaces.get(position).getName());
        holder.address.setText(popularPlaces.get(position).getAddress());
        holder.rating.setRating((float) popularPlaces.get(position).getRating());
        Picasso.get()
                .load(popularPlaces.get(position).getImageUrl())
                .placeholder(R.drawable.icon)
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return popularPlaces.size();
    }

    public interface OnPlacesClickListener {
        void onClickPlace(Places clickedPlace);

        void onClickIcon(Places clickedPlace);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, address;
        RatingBar rating;
        ImageView image;
        RelativeLayout rl_view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_placeName);
            address = itemView.findViewById(R.id.txt_placeAddress);
            rating = itemView.findViewById(R.id.ratingBar);
            image = itemView.findViewById(R.id.img_cityImage);
            rl_view = itemView.findViewById(R.id.rl_view);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClickIcon(popularPlaces.get(getAdapterPosition()));

                }
            });
            rl_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClickPlace(popularPlaces.get(getAdapterPosition()));
                }
            });
        }

    }
}
