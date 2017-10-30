package com.mannydev.dailyapp.controller;

import com.mannydev.dailyapp.model.weather.*;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface WeatherAPI {
    @GET("weather")
    Call<Weather> getWeather(@Query("q") String cityName,@Query("units") String units, @Query("APPID") String appid);

}
