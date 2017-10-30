
package com.mannydev.dailyapp.model.kursvalut;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Nbu__ {

    @SerializedName("buy")
    @Expose
    private String buy;
    @SerializedName("sell")
    @Expose
    private String sell;

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

}
