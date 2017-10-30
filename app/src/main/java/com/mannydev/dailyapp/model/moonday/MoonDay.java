
package com.mannydev.dailyapp.model.moonday;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoonDay {

    @SerializedName("moonday")
    @Expose
    private Integer moonday;
    @SerializedName("date_from")
    @Expose
    private Integer dateFrom;
    @SerializedName("date_to")
    @Expose
    private Integer dateTo;

    public Integer getMoonday() {
        return moonday;
    }

    public void setMoonday(Integer moonday) {
        this.moonday = moonday;
    }

    public Integer getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Integer dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Integer getDateTo() {
        return dateTo;
    }

    public void setDateTo(Integer dateTo) {
        this.dateTo = dateTo;
    }

}
