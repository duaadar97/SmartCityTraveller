package com.pucit.mcproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.gms.maps.MapFragment;
import com.pucit.mcproject.Fragments.CitiesFragment;
import com.pucit.mcproject.Fragments.MapViewFragment;

public class HomeActivity extends AppCompatActivity {

    private TextView tv_city;
    private TextView tv_map;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }
    /**
     * loading City fragment
     */
    private void init() {
        mFragmentManager = getSupportFragmentManager(); //defulat function from app compact
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.frame1, new CitiesFragment()).commit();

        //after the execution of this line frame 1 will be replaced by cities fragment


        //layout attachment
        tv_city=findViewById(R.id.btn_city);
        tv_map=findViewById(R.id.btn_map);


        tv_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new CitiesFragment());
            }
        });
        tv_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new MapViewFragment());
            }
        });
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
