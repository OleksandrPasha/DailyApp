package com.mannydev.dailyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mannydev.dailyapp.weather.WeatherWidget;
import com.squareup.picasso.Picasso;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by manny on 06.10.17.
 */

public class WeatherAdapter extends BaseAdapter {
    private final LayoutInflater lInflater;
    private final ArrayList<WeatherWidget> objects;
    private static final String ICON_WEATHER_BASE_URL = "http://openweathermap.org/img/w/";

    public WeatherAdapter(Context context, ArrayList<WeatherWidget>itemsList) {
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
            view = lInflater.inflate(R.layout.weather_item, parent, false);
        }

        final Animation animation1 = AnimationUtils.loadAnimation(view.getContext(),R.anim.my_animation1);
        final Animation animation2 = AnimationUtils.loadAnimation(view.getContext(),R.anim.my_animation2);
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM", myDateFormatSymbols);
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
        Button btnMoonDay = (Button)view.findViewById(R.id.btnMoonDay);
        btnMoonDay.setText("");
        btnMoonDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MaterialDialog materialDialog = new MaterialDialog(view.getContext());
                materialDialog.setTitle(MainActivity.moonDay.getMoonday()+" Лунный день:");
                materialDialog.setMessage(MainActivity.moonDaysInfo.get(MainActivity.moonDay.getMoonday()-1));
                materialDialog.setPositiveButton("OK", new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        materialDialog.dismiss();
                    }
                });
                materialDialog.show();
            }
        });


        final WeatherWidget item = getWeatherWidget(position);
        Picasso.with(view.getContext()).load(ICON_WEATHER_BASE_URL+item.getIcon()+".png").fit().centerCrop().into((ImageView) view.findViewById(R.id.ivIconWeather));
        ((TextView) view.findViewById(R.id.textDate)).setText(dateFormat.format(currentDate));
        ((TextView)view.findViewById(R.id.textDay)).setText("("+dayFormat.format(currentDate)+")");
        ((TextView) view.findViewById(R.id.txtCity)).setText(item.getCity());
        ((TextView) view.findViewById(R.id.txtTemp)).setText(item.getTemp());
        ((TextView) view.findViewById(R.id.txtWind)).setText(item.getWind());
        ((TextView) view.findViewById(R.id.txtPressure)).setText(item.getPressure());
        ((TextView) view.findViewById(R.id.txtAqua)).setText(item.getAqua());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }

    // Новость по позиции
    private WeatherWidget getWeatherWidget(int position) {
        return ((WeatherWidget) getItem(position));
    }

    private static DateFormatSymbols myDateFormatSymbols = new DateFormatSymbols() {

        @Override
        public String[] getMonths() {
            return new String[]{"января", "февраля", "марта", "апреля", "мая", "июня",
                    "июля", "августа", "сентября", "октября", "ноября", "декабря"};
        }

    };
}
