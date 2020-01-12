package com.pucit.mcproject.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pucit.mcproject.Adapters.CityAdapter;
import com.pucit.mcproject.Models.City;
import com.pucit.mcproject.R;

import java.util.ArrayList;

public class CitiesFragment extends Fragment {

    private static final String FIREBASE_CITY_TABLE_NAME = "Cities";
    private RecyclerView recyclerView;
    private CityAdapter cityAdapter;
    private ArrayList<City> list = new ArrayList<>();

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    private DatabaseReference FIREBASE_DATABASE;
    private DatabaseReference mCityTable;

    public CitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cities, container, false);

        //initiallize fragment manager
        mFragmentManager = getActivity().getSupportFragmentManager();

        //initialize firebase usage
        FirebaseApp.initializeApp(getContext());
        FIREBASE_DATABASE = FirebaseDatabase.getInstance().getReference();

        setupFirebase();//calling firebase get function

        //create adapter with dummy list
        cityAdapter = new CityAdapter(getContext(), list, new CityAdapter.OnCityClickListener() {
            @Override
            public void onClickCityInRecycler(int position) {
                //this will work on cick of any city in recycler view
                Bundle bundle = new Bundle();
                bundle.putSerializable("CityClicked", list.get(position));
                Fragment fragment = new MapViewFragment();
                fragment.setArguments(bundle);
                loadFragment(fragment);
            }
        });
        recyclerView = view.findViewById(R.id.rv_cities);

        //setup layout for recycler
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        //add adapter to recycler
        recyclerView.setAdapter(cityAdapter);


        return view;
    }


    void setupFirebase() {
        mCityTable = FIREBASE_DATABASE.getRef().child(FIREBASE_CITY_TABLE_NAME);

        mCityTable.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {

                    City object = singleSnapshot.getValue(City.class);//create object and parse the data coming from firebase to the city object
                    list.add(object);//insert city data to end of list(adapter's list)
                    //Log.i("kinza", "onDataChange: "+object.toString());
                    cityAdapter.notifyItemInserted(list.size() - 1);//notify recycler adapter that iten is inserted at the end
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
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
