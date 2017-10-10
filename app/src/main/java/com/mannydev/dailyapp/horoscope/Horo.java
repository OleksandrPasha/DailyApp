package com.mannydev.dailyapp.horoscope;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "horo")
public class Horo
{
    @Element(name = "pisces")
    private Pisces pisces;

    @Element(name = "sagittarius")
    private Sagittarius sagittarius;

    @Element(name = "gemini")
    private Gemini gemini;

    @Element(name = "leo")
    private Leo leo;

    @Element(name = "date")
    private Date date;

    @Element(name = "aquarius")
    private Aquarius aquarius;

    @Element(name = "virgo")
    private Virgo virgo;

    @Element(name = "taurus")
    private Taurus taurus;

    @Element(name = "aries")
    private Aries aries;

    @Element(name = "libra")
    private Libra libra;

    @Element(name = "scorpio")
    private Scorpio scorpio;

    @Element(name = "capricorn")
    private Capricorn capricorn;

    @Element(name = "cancer")
    private Cancer cancer;

    public Pisces getPisces ()
    {
        return pisces;
    }

    public void setPisces (Pisces pisces)
    {
        this.pisces = pisces;
    }

    public Sagittarius getSagittarius ()
    {
        return sagittarius;
    }

    public void setSagittarius (Sagittarius sagittarius)
    {
        this.sagittarius = sagittarius;
    }

    public Gemini getGemini ()
    {
        return gemini;
    }

    public void setGemini (Gemini gemini)
    {
        this.gemini = gemini;
    }

    public Leo getLeo ()
    {
        return leo;
    }

    public void setLeo (Leo leo)
    {
        this.leo = leo;
    }

    public Date getDate ()
    {
        return date;
    }

    public void setDate (Date date)
    {
        this.date = date;
    }

    public Aquarius getAquarius ()
    {
        return aquarius;
    }

    public void setAquarius (Aquarius aquarius)
    {
        this.aquarius = aquarius;
    }

    public Virgo getVirgo ()
    {
        return virgo;
    }

    public void setVirgo (Virgo virgo)
    {
        this.virgo = virgo;
    }

    public Taurus getTaurus ()
    {
        return taurus;
    }

    public void setTaurus (Taurus taurus)
    {
        this.taurus = taurus;
    }

    public Aries getAries ()
    {
        return aries;
    }

    public void setAries (Aries aries)
    {
        this.aries = aries;
    }

    public Libra getLibra ()
    {
        return libra;
    }

    public void setLibra (Libra libra)
    {
        this.libra = libra;
    }

    public Scorpio getScorpio ()
    {
        return scorpio;
    }

    public void setScorpio (Scorpio scorpio)
    {
        this.scorpio = scorpio;
    }

    public Capricorn getCapricorn ()
    {
        return capricorn;
    }

    public void setCapricorn (Capricorn capricorn)
    {
        this.capricorn = capricorn;
    }

    public Cancer getCancer ()
    {
        return cancer;
    }

    public void setCancer (Cancer cancer)
    {
        this.cancer = cancer;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [pisces = "+pisces+", sagittarius = "+sagittarius+", gemini = "+gemini+", leo = "+leo+", date = "+date+", aquarius = "+aquarius+", virgo = "+virgo+", taurus = "+taurus+", aries = "+aries+", libra = "+libra+", scorpio = "+scorpio+", capricorn = "+capricorn+", cancer = "+cancer+"]";
    }
}
