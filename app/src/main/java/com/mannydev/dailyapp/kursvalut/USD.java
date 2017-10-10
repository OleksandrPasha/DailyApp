
package com.mannydev.dailyapp.kursvalut;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class USD {

    @SerializedName("interbank")
    @Expose
    private Interbank interbank;
    @SerializedName("nbu")
    @Expose
    private Nbu nbu;

    public Interbank getInterbank() {
        return interbank;
    }

    public void setInterbank(Interbank interbank) {
        this.interbank = interbank;
    }

    public Nbu getNbu() {
        return nbu;
    }

    public void setNbu(Nbu nbu) {
        this.nbu = nbu;
    }

}
