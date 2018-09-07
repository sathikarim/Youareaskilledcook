package com.sthiddov.youareaskilledcook.modils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sthiddov.youareaskilledcook.R;

import java.io.WriteAbortedException;
import java.util.ArrayList;

public class Wsfatadabter extends ArrayAdapter<Wsfat> {

    public Wsfatadabter(Context context, ArrayList<Wsfat> wsfats) {
        super(context, 0, wsfats);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Wsfat wsfat = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_crtg, parent, false);
        }
        // Lookup view for data population
        TextView tvName = convertView.findViewById(R.id.txtwsfat);
        TextView tvtime = convertView.findViewById(R.id.wsfattime);

        ImageView imgwsfat =  convertView.findViewById(R.id.imagwsfat);
        // Populate the data into the template view using the data object
        tvName.setText(wsfat.getTitle());
        tvtime.setText(wsfat.getTime());
        imgwsfat.setImageResource(wsfat.getIdimage());
        // Return the completed view to render on screen
        return convertView;
    }
}
