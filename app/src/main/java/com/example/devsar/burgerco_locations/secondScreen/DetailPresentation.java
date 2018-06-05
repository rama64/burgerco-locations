package com.example.devsar.burgerco_locations.secondScreen;


public interface DetailPresentation {

    void showProgressBar(Boolean action);
    void showError(String error);
    void showImage(String urlImage);
    void showWiFiItem(String wifiText);
    void showServices(String servicesText);
    void showHours(String hoursText);

}
