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

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements  MainPresentation{

    private RecyclerView recyclerView;
    private LocationListAdapter adapter;
    private ProgressBar progressBar;
    private MainPresenter presenter;
    private Disposable disposable;

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

        disposable = presenter.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate( () -> showProgressBar(false))
                .subscribe(
                        locations -> setData(locations),
                        error -> showError(error.getMessage())
                );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        disposable.dispose();
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
