package com.pucit.mcproject.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class City implements Serializable {

    private String CityImage;
    private String CityName;
    private String CityAddress;
    private Location CityLocation;
    private ArrayList<Places> myPlacesLocationModel;

    public City() {

    }

    public String getCityAddress() {
        return CityAddress;
    }

    public void setCityAddress(String cityAddress) {
        CityAddress = cityAddress;
    }

    public Location getCityLocation() {
        return CityLocation;
    }

    public void setCityLocation(Location cityLocation) {
        CityLocation = cityLocation;
    }

    public ArrayList<Places> getMyPlacesLocationModel() {
        return myPlacesLocationModel;
    }

    public void setMyPlacesLocationModel(ArrayList<Places> myPlacesLocationModel) {
        this.myPlacesLocationModel = myPlacesLocationModel;
    }


    public String getCityImage() {
        return CityImage;
    }

    public void setCityImage(String cityImage) {
        CityImage = cityImage;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    @Override
    public String toString() {
        return "City{" +
                "CityImage='" + CityImage + '\'' +
                ", CityName='" + CityName + '\'' +
                ", CityAddress='" + CityAddress + '\'' +
                ", CityLocation=" + CityLocation +
                ", myPlacesLocationModel=" + myPlacesLocationModel +
                '}';
    }
}
