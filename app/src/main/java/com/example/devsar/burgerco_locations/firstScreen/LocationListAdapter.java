package com.example.devsar.burgerco_locations.firstScreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.devsar.burgerco_locations.R;
import com.example.devsar.burgerco_locations.model.Location;
import com.example.devsar.burgerco_locations.secondScreen.DetailActivity;


import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by Ramiro on 27/04/2018.
 */

public class LocationListAdapter extends RecyclerView.Adapter<LocationListAdapter.ViewHolderList> {

    private List<Location> locations;

    public LocationListAdapter(List<Location> location) {
        this.locations = location;
    }

    @Override
    public ViewHolderList onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_list_layout,parent,false);
        ViewHolderList holder = new ViewHolderList(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolderList holder, final int position) {
       Location location = locations.get(position);
       holder.bindTo(location);
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public class ViewHolderList extends RecyclerView.ViewHolder {
        TextView textViewName, textViewID;
        ImageView imageViewImage;
        public ViewHolderList(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.name);
            textViewID = itemView.findViewById(R.id.id);
            imageViewImage = itemView.findViewById(R.id.image);
        }

        public void bindTo(Location location){
            textViewName.setText(location.getName());
            textViewID.setText(location.getId());
            imageViewImage.setTransitionName(location.getId());
            Glide.with(imageViewImage.getContext())
                    .load(location.getUrlImage())
                    .apply(RequestOptions.centerCropTransform())
                    .transition(withCrossFade())
                    .into(imageViewImage);

            imageViewImage.setOnClickListener(view -> {
                Intent intent = new Intent(imageViewImage.getContext(), DetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) imageViewImage.getContext(), imageViewImage, "image");
                intent.putExtra("id", location.getId());
                imageViewImage.getContext().startActivity(intent, options.toBundle());

            });
        }
    }

}


