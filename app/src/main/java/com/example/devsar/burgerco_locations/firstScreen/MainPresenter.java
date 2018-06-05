package com.example.devsar.burgerco_locations.firstScreen;

import com.example.devsar.burgerco_locations.model.Location;
import com.example.devsar.burgerco_locations.retrofit.MyApiInterface;
import com.example.devsar.burgerco_locations.retrofit.RetrofitSingleton;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {

    private MainPresentation presentation;

    public MainPresenter(MainPresentation presentation){
        this.presentation = presentation;
    }

    void onCreate(){
        if (presentation == null) return;
        presentation.showProgressBar(true);

        RetrofitSingleton.getInstance()
                .create(MyApiInterface.class)
                .getLocations()
                .enqueue(new Callback<List<Location>>() {
                    @Override
                    public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                        if (presentation == null) return;
                        presentation.showProgressBar(false);
                        presentation.setData(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Location>> call, Throwable t) {
                        if (presentation == null) return;
                        presentation.showProgressBar(false);
                        presentation.showError(t.getMessage());
                    }
                });
    }


    public void onDestroy() {
        presentation = null;
    }
}