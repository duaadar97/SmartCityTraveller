package com.pucit.mcproject.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pucit.mcproject.Adapters.PlacesAdapter;
import com.pucit.mcproject.HomeActivity;
import com.pucit.mcproject.Models.City;
import com.pucit.mcproject.Models.Places;
import com.pucit.mcproject.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class CityDetailFragment extends Fragment {

    ImageView img_cityImage;
    TextView txt_cityName;
    TextView txt_cityAddress;
    LinearLayout ll_map_btn;
    RecyclerView rv_places;
    PlacesAdapter placesAdapter;

    private City mCity;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    public CityDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_city_detail, container, false);

        //connecting ui elements to objects
        img_cityImage = view.findViewById(R.id.img_cityImage);
        txt_cityName = view.findViewById(R.id.txt_cityName);
        txt_cityAddress = view.findViewById(R.id.txt_cityAddress);
        ll_map_btn = view.findViewById(R.id.ll_map_btn);
        rv_places = view.findViewById(R.id.rv_places);

        //initiallize fragment manager
        mFragmentManager = getActivity().getSupportFragmentManager();

        ((HomeActivity) getActivity()).hideTabs();
        //getting bundled arguments of a single city
        Bundle bundle = getArguments();
        if (bundle != null) {
            mCity = (City) bundle.getSerializable("CityClicked");
            txt_cityName.setText(mCity.getCityName());
            txt_cityAddress.setText(mCity.getCityAddress());
            //load Image
            Picasso.get()
                    .load(mCity.getCityImage())
                    .placeholder(R.mipmap.ic_launcher_round)
                    .into(img_cityImage);

            ll_map_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("CityClicked", mCity);
                    Fragment fragment = new MapViewFragment();
                    fragment.setArguments(bundle);
                    loadFragment(fragment);
                }
            });
            //setting up places adapter for data population
            placesAdapter = new PlacesAdapter(getContext(), mCity.getMyPlacesLocationModel(), new PlacesAdapter.OnPlacesClickListener() {
                @Override
                public void onClickPlace(Places clickedPlace) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("PlaceClicked", clickedPlace);
                    Fragment fragment = new MapViewFragment();
                    fragment.setArguments(bundle);
                    loadFragment(fragment);
                }

                @Override
                public void onClickIcon(Places clickedPlace) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("PlaceClicked", clickedPlace);
                    Fragment fragment = new BookingFragment();
                    fragment.setArguments(bundle);
                    loadFragment(fragment);
                }
            });

            //setting up the recycler view
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setStackFromEnd(true);
            rv_places.setLayoutManager(layoutManager);

            //adding adapter to recycler view
            rv_places.setAdapter(placesAdapter);
        }
        return view;
    }
    /**
     * @param fragment fragment to load
     * @return true if loaded other wise false(only false when send a null fragment)
     */
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.add(fragment, "detail") // Add this transaction to the back stack (name is an optional name for this back stack state, or null).
                    .addToBackStack(null);
            mFragmentTransaction.replace(R.id.frame1, fragment).commit();
            return true;
        }
        return false;
    }
}
