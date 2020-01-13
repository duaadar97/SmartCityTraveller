package com.pucit.mcproject.Fragments;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.pucit.mcproject.HomeActivity;
import com.pucit.mcproject.Models.City;
import com.pucit.mcproject.Models.Places;
import com.pucit.mcproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapViewFragment extends Fragment {

    Location currentloaction;
    FusedLocationProviderClient fusedLocationProviderClient;
    GoogleMap mMap;
    City mCity = null;
    Places mPlace = null;
    List<Marker> markers = new ArrayList<>();//to store markers we add that wil be used to show the area in which markers are present
    private static final int Request_code = 101;

    public MapViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map_view, container, false);

        //getting arguments bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.containsKey("CityClicked")) {
                mCity = (City) bundle.getSerializable("CityClicked");
            } else {
                mPlace = (Places) bundle.getSerializable("PlaceClicked");
            }
        }


        //initializing map
        MapView mapView = (MapView) view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.onResume(); // needed to get the map to display immediately
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mMap.getUiSettings().setAllGesturesEnabled(true);
                mMap.getUiSettings().setScrollGesturesEnabled(true);
                mMap.getUiSettings().setCompassEnabled(false);
                mMap.getUiSettings().setMapToolbarEnabled(false);
                mMap.getUiSettings().setZoomControlsEnabled(false);

            }
        });
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        fetchLastLocation();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showTabs();
    }

    private void showCityData() {
        for (Places places : mCity.getMyPlacesLocationModel()) {
            LatLng latLng = new LatLng(places.getLocation().getLatitude(), places.getLocation().getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title(places.getName());

            //adding marker on map
            Marker marker = mMap.addMarker(markerOptions);

            //adding marker details to list
            markers.add(marker);
        }
        moveCameraAfterSearchToLocation(markers);


    }

    private void moveCameraAfterSearchToLocation(List<Marker> markers) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Marker marker : markers) {
            builder.include(marker.getPosition());
        }
        LatLngBounds bounds = builder.build();
        int padding = 120; // offset from edges of the map in pixels
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        mMap.animateCamera(cu);

    }

    private void fetchLastLocation() {
        //check location permission
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, Request_code);
            return;
        }

        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentloaction = location;
                    if (mCity != null) {
                        showCityData(); //shw city data if have
                    } else if (mPlace != null) {
                        showPlaceData();
                    } else      //else show current position
                        moveCameraToLocation(currentloaction.getLatitude(), currentloaction.getLongitude());
                }
            }
        });

    }

    private void showPlaceData() {
        LatLng latLng = new LatLng(mPlace.getLocation().getLatitude(), mPlace.getLocation().getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(mPlace.getName());

        //adding marker on map
        mMap.addMarker(markerOptions);

        moveCameraToLocation(mPlace.getLocation().getLatitude(), mPlace.getLocation().getLongitude());
    }

    private void moveCameraToLocation(double latitude, double longitude) {

        LatLng mLatLng = new LatLng(latitude, longitude);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(mLatLng)
                .zoom(16)
                .bearing(30)
                .build();
        CameraUpdate cu = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.animateCamera(cu);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Request_code:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchLastLocation();
                }
                break;
        }
    }

}
