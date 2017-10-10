
package com.mannydev.dailyapp.kursvalut;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KursValut {

    @SerializedName("USD")
    @Expose
    private USD uSD;
    @SerializedName("EUR")
    @Expose
    private EUR eUR;
    @SerializedName("RUB")
    @Expose
    private RUB rUB;

    public USD getUSD() {
        return uSD;
    }

    public void setUSD(USD uSD) {
        this.uSD = uSD;
    }

    public EUR getEUR() {
        return eUR;
    }

    public void setEUR(EUR eUR) {
        this.eUR = eUR;
    }

    public RUB getRUB() {
        return rUB;
    }

    public void setRUB(RUB rUB) {
        this.rUB = rUB;
    }

}
