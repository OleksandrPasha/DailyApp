package com.mannydev.dailyapp.anekdot;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root (name = "image")
public class Image
{
    @Element(name = "title")
    private String title;
    @Element(name = "height")
    private String height;
    @Element(name = "description")
    private String description;
    @Element(name = "link")
    private String link;
    @Element(name = "width")
    private String width;
    @Element(name = "url")
    private String url;

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getHeight ()
    {
        return height;
    }

    public void setHeight (String height)
    {
        this.height = height;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getLink ()
    {
        return link;
    }

    public void setLink (String link)
    {
        this.link = link;
    }

    public String getWidth ()
    {
        return width;
    }

    public void setWidth (String width)
    {
        this.width = width;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [title = "+title+", height = "+height+", description = "+description+", link = "+link+", width = "+width+", url = "+url+"]";
    }
}
