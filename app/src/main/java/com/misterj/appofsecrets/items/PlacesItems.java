package com.misterj.appofsecrets.items;

public class PlacesItems {

    public Integer id;
    public String name;
    public Integer category;
    public Integer status;
    public String country;
    public String state;
    public Double lat;
    public Double lng;
    public Integer sponsored;
    public String image;
    public Integer verified;
    public String created_at;
    public Double distance;

    public PlacesItems(Integer id, String name, Integer category, Integer status, String country, String state, Double lat, Double lng, Integer sponsored, String image, int verified, String created_at, Double distance) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.status = status;
        this.country = country;
        this.state = state;
        this.lat = lat;
        this.lng = lng;
        this.sponsored = sponsored;
        this.image = image;
        this.verified = verified;
        this.created_at = created_at;
        this.distance = distance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Integer getSponsored() {
        return sponsored;
    }

    public void setSponsored(Integer sponsored) {
        this.sponsored = sponsored;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getVerified() {
        return verified;
    }

    public void setVerified(Integer verified) {
        this.verified = verified;
    }
}
