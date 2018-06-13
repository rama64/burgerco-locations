package com.example.devsar.burgerco_locations.retrofit;


import com.example.devsar.burgerco_locations.model.DetailItem;
import com.example.devsar.burgerco_locations.model.Location;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyApiInterface {

    @GET("/locations")
    Single<List<Location>> getLocations();

    @GET("/locations/{id}")
    Call<DetailItem> getLocationInfo(@Path("id") String id);

}
