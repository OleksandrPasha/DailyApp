package com.mannydev.dailyapp.controller;

import com.mannydev.dailyapp.model.moonday.MoonDay;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by manny on 30.10.17.
 */

public interface MoonDayAPI {
    @GET("?get=moonday")
    Call<MoonDay> getMoonDay();
}
