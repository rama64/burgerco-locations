package com.example.devsar.burgerco_locations.firstScreen;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.devsar.burgerco_locations.R;
import com.example.devsar.burgerco_locations.model.Location;

import java.util.List;

public class MainActivity extends AppCompatActivity implements  MainPresentation{

    private RecyclerView recyclerView;
    private LocationListAdapter adapter;
    private ProgressBar progressBar;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = findViewById(R.id.progressBar);
        presenter = new MainPresenter(this);
        presenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void showProgressBar(Boolean action) {
        progressBar.setVisibility(action? View.VISIBLE : View.GONE);
    }

    @Override
    public void setData(List<Location> locations) {
        adapter = new LocationListAdapter(locations);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError(String error) {
        Snackbar.make(recyclerView, error, Snackbar.LENGTH_INDEFINITE)
                .setAction("Ok", v -> {})
                .show();
    }

}
