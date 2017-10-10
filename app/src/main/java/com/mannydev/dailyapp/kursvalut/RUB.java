
package com.mannydev.dailyapp.kursvalut;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RUB {

    @SerializedName("nbu")
    @Expose
    private Nbu__ nbu;

    public Nbu__ getNbu() {
        return nbu;
    }

    public void setNbu(Nbu__ nbu) {
        this.nbu = nbu;
    }

}
