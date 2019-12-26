package com.pucit.mcproject.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;
import com.pucit.mcproject.Adapters.CityAdapter;
import com.pucit.mcproject.Models.City;
import com.pucit.mcproject.R;

import java.util.ArrayList;

public class CitiesFragment extends Fragment {

    private RecyclerView recyclerView;
    private CityAdapter cityAdapter;
    ArrayList<City> list=new ArrayList<>();
    public CitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void fetchWorldCities(){
        //fetching code goes here!

        //populate list
        //list.add("city data")
        //cityAdapter.notifyDataSetChanged();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cities, container, false);


        list.add(new City("Lahore","https://dynaimage.cdn.cnn.com/cnn/q_auto,w_900,c_fill,g_auto,h_506,ar_16:9/http%3A%2F%2Fcdn.cnn.com%2Fcnnnext%2Fdam%2Fassets%2F190123183436-lahore-1042hr.jpg",new LatLng(31.4826352,74.0542001)));

        //create adapter with dummy list
        cityAdapter =new CityAdapter(getContext(),list);
        recyclerView=view.findViewById(R.id.rv_cities);

        //setup layout for recycler
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        //add adapter to recycler
        recyclerView.setAdapter(cityAdapter);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
