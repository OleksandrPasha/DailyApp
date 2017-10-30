package com.mannydev.dailyapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mannydev.dailyapp.MainActivity;
import com.mannydev.dailyapp.R;
import com.mannydev.dailyapp.model.moonday.MoonDay;
import com.mannydev.dailyapp.model.weather.WeatherWidget;
import com.squareup.picasso.Picasso;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

import me.drakeet.materialdialog.MaterialDialog;

import static com.mannydev.dailyapp.R.id.btnMoonDay;


/**
 * Created by manny on 06.10.17.
 */

public class WeatherAdapter extends BaseAdapter {
    private  static final String DAY_FORMAT = "EEEE";
    private  static final String DATE_FORMAT = "dd MMMM";
    private static final String ICON_WEATHER_BASE_URL = "http://openweathermap.org/img/w/";

    private LayoutInflater lInflater;
    private ArrayList<WeatherWidget> objects;
    private Context context;
    private Date currentDate;
    private MoonDay moonDay;
    private String moonDayInfo;
    private MaterialDialog materialDialog;
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat dayFormat;

    public WeatherAdapter(Context context) {
        objects = new ArrayList<>();
        moonDay = new MoonDay();
        moonDayInfo = new String();
        this.context = context;
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

        View view = convertView;

        lInflater = (LayoutInflater) parent.getContext()
                .getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            view = lInflater.inflate(R.layout.weather_item, parent, false);
        }

        Button btnMoonDay = view.findViewById(R.id.btnMoonDay);
        btnMoonDay.setText("");
        btnMoonDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("MoonDay :"+moonDay.getMoonday()+" Info :"+moonDayInfo);
                materialDialog = new MaterialDialog(context);
                materialDialog.setTitle(moonDay.getMoonday()+" Лунный день:");
                materialDialog.setMessage(moonDayInfo);
                materialDialog.setPositiveButton("OK", new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        materialDialog.dismiss();
                    }
                });
                materialDialog.show();
            }
        });


        WeatherWidget item = (WeatherWidget)getItem(position);

        Picasso.with(view.getContext())
                .load(ICON_WEATHER_BASE_URL+item.getIcon()+".png")
                .fit().centerCrop()
                .into((ImageView) view.findViewById(R.id.ivIconWeather));

        ((TextView) view.findViewById(R.id.textDate)).setText(dateFormat.format(currentDate));
        ((TextView)view.findViewById(R.id.textDay)).setText("("+dayFormat.format(currentDate)+")");
        ((TextView) view.findViewById(R.id.txtCity)).setText(item.getCity());
        ((TextView) view.findViewById(R.id.txtTemp)).setText(item.getTemp());
        ((TextView) view.findViewById(R.id.txtWind)).setText(item.getWind());
        ((TextView) view.findViewById(R.id.txtPressure)).setText(item.getPressure());
        ((TextView) view.findViewById(R.id.txtAqua)).setText(item.getAqua());

        return view;
    }

    public void setData(ArrayList<WeatherWidget>itemsList, MoonDay moonDay, String moonDayInfo){
        this.objects = itemsList;
        this.moonDay = moonDay;
        this.moonDayInfo = moonDayInfo;
        currentDate = new Date();
        dateFormat = new SimpleDateFormat(DATE_FORMAT, myDateFormatSymbols);
        dayFormat = new SimpleDateFormat(DAY_FORMAT, new Locale("RU"));
    }


    private DateFormatSymbols myDateFormatSymbols = new DateFormatSymbols() {

        @Override
        public String[] getMonths() {
            return new String[]{"января", "февраля", "марта", "апреля", "мая", "июня",
                    "июля", "августа", "сентября", "октября", "ноября", "декабря"};
        }

    };
}
