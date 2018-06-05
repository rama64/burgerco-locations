package com.example.devsar.burgerco_locations.secondScreen;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.devsar.burgerco_locations.R;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class DetailActivity extends AppCompatActivity implements DetailPresentation {

    private DetailPresenter presenter;
    private ProgressBar progressBar;
    private TextView servicesTextView, wifiTextView, hoursTextView;
    private ImageView imageViewImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        supportPostponeEnterTransition();
        progressBar = findViewById(R.id.progressBar);
        servicesTextView = findViewById(R.id.services);
        wifiTextView = findViewById(R.id.wifi);
        hoursTextView = findViewById(R.id.hours);
        imageViewImage = findViewById(R.id.imageViewDetail);
        String idItem = getIDItemSelected();
        if(idItem != null){
            presenter = new DetailPresenter(this);
            presenter.bringData(idItem);
        }
    }

    public String getIDItemSelected() {
        Intent intent = getIntent();
        if(intent.hasExtra("id")){
            return intent.getStringExtra("id");
        }
        Toast.makeText(this,"error trying to get the id of the element",Toast.LENGTH_LONG).show();
        return null;
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
    public void showError(String error) {
        Snackbar.make(findViewById(R.id.services), error, Snackbar.LENGTH_INDEFINITE)
                .setAction("Ok", v -> {})
                .show();
    }

    @Override
    public void showImage(String urlImage) {
        Glide.with(this)
                .load(urlImage)
                .apply(RequestOptions.centerCropTransform())
                .transition(withCrossFade())
                .into(imageViewImage);
        supportStartPostponedEnterTransition();
    }

    @Override
    public void showWiFiItem(String wifiText) {
        wifiTextView.setText(wifiText);
    }

    @Override
    public void showServices(String servicesText) {
        servicesTextView.setText(servicesText);
    }

    @Override
    public void showHours(String hoursText) {
        hoursTextView.setText(hoursText);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
