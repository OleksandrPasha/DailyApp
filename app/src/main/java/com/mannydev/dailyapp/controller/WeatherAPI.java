package com.mannydev.dailyapp.controller;

import com.mannydev.dailyapp.horoscope.Horo;
import com.mannydev.dailyapp.weather.*;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by manny on 06.10.17.
 */

public interface WeatherAPI {
    @GET("weather")
    Call<Weather> getWeather(@Query("q") String cityName,@Query("units") String units, @Query("APPID") String appid);

}
