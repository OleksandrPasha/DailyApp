package com.mannydev.dailyapp.controller;

import com.mannydev.dailyapp.kursvalut.KursValut;
import com.mannydev.dailyapp.weather.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by manny on 07.10.17.
 */

public interface KursValutAPI {
    @GET("rates")
    Call<KursValut> getKurs();
}
