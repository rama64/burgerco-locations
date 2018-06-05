package com.example.devsar.burgerco_locations.firstScreen;

import com.example.devsar.burgerco_locations.model.Location;


import java.util.List;

public interface MainPresentation {

    void showProgressBar(Boolean action);
    void setData(List<Location> locations);
    void showError(String error);

}
