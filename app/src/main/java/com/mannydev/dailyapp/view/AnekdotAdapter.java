package com.mannydev.dailyapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mannydev.dailyapp.R;
import com.mannydev.dailyapp.model.anekdot.Anekdot;

import java.util.ArrayList;

/**
 * Created by manny on 08.10.17.
 */

public class AnekdotAdapter extends BaseAdapter {
    private LayoutInflater lInflater;
    private ArrayList<Anekdot> anekdots;

    public AnekdotAdapter() {
        anekdots = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return anekdots.size();
    }

    @Override
    public Object getItem(int position) {
        return anekdots.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        lInflater = (LayoutInflater) parent.getContext()
                .getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            view = lInflater.inflate(R.layout.anekdot_item, parent, false);
        }

        Anekdot item = (Anekdot)getItem(position);

        ((TextView) view.findViewById(R.id.txtAnekdot)).setText(item.getElementPureHtml()
                .replace("<br />","").replace("&quot;",""));

        return view;
    }

    public void setData(ArrayList<Anekdot> list){
        this.anekdots = list;
    }
}
