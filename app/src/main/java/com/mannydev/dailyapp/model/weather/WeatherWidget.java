package com.mannydev.dailyapp.model.weather;

/**
 * Created by manny on 06.10.17.
 */

public class WeatherWidget {
    private String temp;
    private String text;
    private String wind;
    private String pressure;
    private String aqua;
    private String icon;
    private String city;

    public WeatherWidget(String temp, String wind, String pressure, String aqua, String icon, String city) {
        this.temp = temp;
        this.wind = wind;
        this.pressure = pressure;
        this.aqua = aqua;
        this.icon = icon;
        this.city = city;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getAqua() {
        return aqua;
    }

    public void setAqua(String aqua) {
        this.aqua = aqua;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
