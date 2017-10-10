package com.mannydev.dailyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mannydev.dailyapp.anekdot.Anekdot;
import com.mannydev.dailyapp.weather.WeatherWidget;

import java.util.ArrayList;

/**
 * Created by manny on 08.10.17.
 */

public class AnekdotAdapter extends BaseAdapter {
    private final LayoutInflater lInflater;
    private final ArrayList<Anekdot> objects;

    public AnekdotAdapter(Context context, ArrayList<Anekdot>itemsList) {
        Context ctx = context;
        objects = itemsList;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.anekdot_item, parent, false);
        }

        final Anekdot item = getAnekdot(position);


        ((TextView) view.findViewById(R.id.txtAnekdot)).setText(item.getElementPureHtml().replace("<br />","").replace("&quot;",""));


        return view;
    }

    // Новость по позиции
    private Anekdot getAnekdot(int position) {
        return ((Anekdot) getItem(position));
    }
}
