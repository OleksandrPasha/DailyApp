package com.mannydev.dailyapp.controller;

import com.mannydev.dailyapp.horoscope.Horo;


import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;



public interface HoroscopeAPI {

    @GET("/r/export/utf/xml/daily/com.xml")
    Call<Horo> getHoro();

}
