package com.mannydev.dailyapp.controller;

import com.mannydev.dailyapp.model.horoscope.Horo;


import retrofit2.Call;
import retrofit2.http.GET;

public interface HoroscopeAPI {

    @GET("/r/export/utf/xml/daily/com.xml")
    Call<Horo> getHoro();

}
