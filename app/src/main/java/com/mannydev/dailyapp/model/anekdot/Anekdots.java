package com.mannydev.dailyapp.model.anekdot;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by manny on 30.10.17.
 */

public class Anekdots {

    @SerializedName("list")
    @Expose
    private ArrayList<Anekdot>list;

    public ArrayList<Anekdot> getList() {
        return list;
    }

    public void setList(ArrayList<Anekdot> list) {
        this.list = list;
    }
}
