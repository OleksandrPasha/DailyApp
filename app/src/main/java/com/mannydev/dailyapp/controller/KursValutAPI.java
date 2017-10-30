package com.mannydev.dailyapp.controller;

import com.mannydev.dailyapp.model.kursvalut.KursValut;

import retrofit2.Call;
import retrofit2.http.GET;


public interface KursValutAPI {
    @GET("rates")
    Call<KursValut> getKurs();
}
