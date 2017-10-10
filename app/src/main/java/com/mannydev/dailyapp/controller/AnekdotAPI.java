package com.mannydev.dailyapp.controller;

import com.mannydev.dailyapp.anekdot.Anekdot;
import com.mannydev.dailyapp.anekdot.Rss;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by manny on 08.10.17.
 */

public interface AnekdotAPI {

    @GET("/get")
    Call<List<Anekdot>> getAnekdots(@Query("site")String site, @Query("name") String resourceName, @Query("num") int count);
}