package com.mannydev.dailyapp.model.horoscope;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "aquarius")
public class Aquarius
{
    @Element(name = "tomorrow")
    private String tomorrow;

    @Element(name = "tomorrow02")
    private String tomorrow02;

    @Element(name = "yesterday")
    private String yesterday;

    @Element(name = "today")
    private String today;

    public String getTomorrow ()
    {
        return tomorrow;
    }

    public void setTomorrow (String tomorrow)
    {
        this.tomorrow = tomorrow;
    }

    public String getTomorrow02 ()
    {
        return tomorrow02;
    }

    public void setTomorrow02 (String tomorrow02)
    {
        this.tomorrow02 = tomorrow02;
    }

    public String getYesterday ()
    {
        return yesterday;
    }

    public void setYesterday (String yesterday)
    {
        this.yesterday = yesterday;
    }

    public String getToday ()
    {
        return today;
    }

    public void setToday (String today)
    {
        this.today = today;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [tomorrow = "+tomorrow+", tomorrow02 = "+tomorrow02+", yesterday = "+yesterday+", today = "+today+"]";
    }
}
