package com.example.devsar.burgerco_locations.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramiro on 27/04/2018.
 */

public class DetailItem {

    private String id;
    private String services;
    @SerializedName("hours_of_operation")
    private String hours;
    private Boolean wifi;
    @SerializedName("photo")
    private String imageUrl;

    public DetailItem(String id, String services, String hours, Boolean wifi,String imageUrl) {
        this.id = id;
        this.services = services;
        this.hours = hours;
        this.wifi = wifi;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
