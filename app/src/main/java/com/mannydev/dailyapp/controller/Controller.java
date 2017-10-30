package com.mannydev.dailyapp.controller;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mannydev.dailyapp.model.horoscope.Horo;
import com.mannydev.dailyapp.model.weather.Weather;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by manny on 05.10.17.
 */

public class Controller {

    public static final String BASE_WEATHER_URL = "http://api.openweathermap.org/data/2.5/";
    public static final String BASE_HORO_URL = "http://img.ignio.com/";
    public static final String BASE_KURS_URL = "http://openrates.in.ua/";
    public static final String BASE_ANEKDOT_URL = "http://umorili.herokuapp.com/api/";
    public static final String BASE_MOONDAY_URL = "http://moon-today.com/api/";

    public static WeatherAPI getWeatherApi(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_WEATHER_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.v("myLogs", retrofit.converterFactories().toString());
        WeatherAPI weatherAPI = retrofit.create(WeatherAPI.class);
        Log.v("myLogs", weatherAPI.toString());
        return weatherAPI;
    }

    public static HoroscopeAPI getHoroApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_HORO_URL)
                .client(new OkHttpClient())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        HoroscopeAPI horoscopeAPI = retrofit.create(HoroscopeAPI.class);
        return  horoscopeAPI;
    }

    public static KursValutAPI getKursApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_KURS_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        KursValutAPI kursValutAPI = retrofit.create(KursValutAPI.class);
        return kursValutAPI;
    }

    public static AnekdotAPI getAnekdotsAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_ANEKDOT_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AnekdotAPI anekdotAPI = retrofit.create(AnekdotAPI.class);
        return anekdotAPI;
    }

    public static MoonDayAPI getMoonDayAPI(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_MOONDAY_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        MoonDayAPI moonDayAPI = retrofit.create(MoonDayAPI.class);
        return moonDayAPI;
    }
}
