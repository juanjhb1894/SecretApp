package com.example.myapplication.items;

public class PlacesItems {

    public int id;
    public String iamge_url;
    public String name;
    public String description;
    public double latitude;
    public double longitude;
    public double distance;

    public PlacesItems(int id, String iamge_url, String name, String description, double latitude, double longitude, double distance) {
        this.id = id;
        this.iamge_url = iamge_url;
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIamge_url() {
        return iamge_url;
    }

    public void setIamge_url(String iamge_url) {
        this.iamge_url = iamge_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
