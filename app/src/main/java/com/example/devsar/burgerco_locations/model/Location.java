package com.example.devsar.burgerco_locations.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramiro on 27/04/2018.
 */

public class Location {

    private String id;
    private String name;
    @SerializedName("photo")
    private String urlImage;

    public Location(String id, String name, String urlImage) {
        this.id = id;
        this.name = name;
        this.urlImage = urlImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
