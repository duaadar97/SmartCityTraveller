package com.pucit.mcproject.Models;

public class Places {
    private String Address;
    private double rating;
    private String ImageUrl;
    private Location location;
    private String Name;

    public Places() {
    }

    public Places(String address, double rating, String imageUrl, Location location, String name) {
        Address = address;
        this.rating = rating;
        ImageUrl = imageUrl;
        this.location = location;
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "Places{" +
                "Address='" + Address + '\'' +
                ", rating=" + rating +
                ", ImageUrl='" + ImageUrl + '\'' +
                ", location=" + location +
                ", Name='" + Name + '\'' +
                '}';
    }
}
