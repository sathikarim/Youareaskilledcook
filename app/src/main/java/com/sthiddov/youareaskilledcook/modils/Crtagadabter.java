package com.sthiddov.youareaskilledcook.modils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sthiddov.youareaskilledcook.R;

import java.util.ArrayList;

public class Crtagadabter extends ArrayAdapter<Crtag> {

    public Crtagadabter(Context context, ArrayList<Crtag> crtags) {
        super(context, 0, crtags);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Crtag crtag = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_crtg, parent, false);
        }
        // Lookup view for data population
        TextView tvName = convertView.findViewById(R.id.txtcrtg);
        ImageView imgcartg =  convertView.findViewById(R.id.imagwsfat);
        // Populate the data into the template view using the data object
        tvName.setText(crtag.getTitle());
        imgcartg.setImageBitmap(crtag.getBitmap());
        // Return the completed view to render on screen
        return convertView;
    }
}
