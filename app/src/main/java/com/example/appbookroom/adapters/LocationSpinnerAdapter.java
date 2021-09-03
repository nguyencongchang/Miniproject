package com.example.appbookroom.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appbookroom.R;
import com.example.appbookroom.entity.Location;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LocationSpinnerAdapter extends ArrayAdapter<Location> {

    public LocationSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<Location> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected_location, parent, false);
        TextView tvNameTowerSelected = convertView.findViewById(R.id.tvItemSelectLocation);
        Location location = this.getItem(position);
        if (location != null) {
            tvNameTowerSelected.setText(location.getName());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull @NotNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spinner_location, parent, false);
        TextView tvNameTower = convertView.findViewById(R.id.spNameTower);
        Location location = this.getItem(position);
        if (location != null) {
            tvNameTower.setText(location.getName());
        }
        return convertView;
    }
}
