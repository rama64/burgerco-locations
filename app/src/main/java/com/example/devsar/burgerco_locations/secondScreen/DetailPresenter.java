package com.example.devsar.burgerco_locations.secondScreen;


import com.example.devsar.burgerco_locations.model.DetailItem;
import com.example.devsar.burgerco_locations.retrofit.MyApiInterface;
import com.example.devsar.burgerco_locations.retrofit.RetrofitSingleton;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenter {

    private DetailPresentation presentation;

    public DetailPresenter(DetailPresentation presentation){
        this.presentation = presentation;
    }

    void bringData(String locationID){
        if (presentation == null) return;
        presentation.showProgressBar(true);
        RetrofitSingleton.getInstance().create(MyApiInterface.class)
                .getLocationInfo(locationID)
                .enqueue(new Callback<DetailItem>() {
                    @Override
                    public void onResponse(Call<DetailItem> call, Response<DetailItem> response) {
                        if (presentation == null) return;
                        presentation.showProgressBar(false);
                        presentation.showImage(response.body().getImageUrl());
                        presentation.showServices(response.body().getServices());
                        presentation.showWiFiItem(response.body().getWifi()?"WiFi":"No WiFi" );
                        presentation.showHours("Hours: "+response.body().getHours());
                    }
                    @Override
                    public void onFailure(Call<DetailItem> call, Throwable t) {
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
