package com.mannydev.dailyapp.controller;

import com.mannydev.dailyapp.model.anekdot.Anekdot;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AnekdotAPI {
    @GET("get?site=anekdot.ru&name=new+anekdot&num=50")
    Call<ArrayList<Anekdot>> getAnekdots();
}
