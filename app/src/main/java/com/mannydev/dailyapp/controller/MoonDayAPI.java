package com.mannydev.dailyapp.controller;

import com.mannydev.dailyapp.model.moonday.MoonDay;


import retrofit2.Call;
import retrofit2.http.GET;


public interface MoonDayAPI {
    @GET("?get=moonday")
    Call<MoonDay> getMoonDay();
}
