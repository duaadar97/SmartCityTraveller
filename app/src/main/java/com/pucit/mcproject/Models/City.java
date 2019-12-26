package com.pucit.mcproject.Models;

import com.google.android.gms.maps.model.LatLng;

public class City {
    private String name;
    private String image;
    private LatLng latLng;

    public City() {

    }

    public City(String name, String image, LatLng latLng) {
        this.name = name;
        this.image = image;
        this.latLng = latLng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
}
