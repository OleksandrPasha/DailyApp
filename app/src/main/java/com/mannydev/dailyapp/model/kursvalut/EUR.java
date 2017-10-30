
package com.mannydev.dailyapp.model.kursvalut;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EUR {

    @SerializedName("nbu")
    @Expose
    private Nbu_ nbu;

    public Nbu_ getNbu() {
        return nbu;
    }

    public void setNbu(Nbu_ nbu) {
        this.nbu = nbu;
    }

}
