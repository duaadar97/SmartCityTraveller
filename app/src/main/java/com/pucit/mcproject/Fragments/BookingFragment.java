package com.pucit.mcproject.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pucit.mcproject.Models.City;
import com.pucit.mcproject.Models.Places;
import com.pucit.mcproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingFragment extends Fragment {

    private Places mPlace;

    public BookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booking, container, false);
        //getting arguments bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            mPlace = (Places) bundle.getSerializable("PlaceClicked");
            performBooking();
        }

        return view;
    }

    private void performBooking() {
        Toast.makeText(getContext(), "Performing booking on " + mPlace.getName(), Toast.LENGTH_SHORT).show();
    }

}
