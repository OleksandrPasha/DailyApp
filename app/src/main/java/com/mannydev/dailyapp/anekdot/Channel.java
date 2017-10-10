package com.mannydev.dailyapp.anekdot;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "channel")
public class Channel
{
    @Element(name = "pubDate")
    private String pubDate;
    @Element(name = "title")
    private String title;
    @Element(name = "managingEditor")
    private String managingEditor;
    @Element(name = "description")
    private String description;
    @Element(name = "link")
    private String link;
    @Element(name = "lastBuildDate")
    private String lastBuildDate;
    @Element(name = "item")
    private ArrayList<Item> items;
    @Element(name = "image")
    private Image image;
    @Element(name = "language")
    private String language;
    @Element(name = "copyright")
    private String copyright;
    @Element(name = "webMaster")
    private String webMaster;

    public String getPubDate ()
    {
        return pubDate;
    }

    public void setPubDate (String pubDate)
    {
        this.pubDate = pubDate;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getManagingEditor ()
    {
        return managingEditor;
    }

    public void setManagingEditor (String managingEditor)
    {
        this.managingEditor = managingEditor;
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

    public String getLastBuildDate ()
    {
        return lastBuildDate;
    }

    public void setLastBuildDate (String lastBuildDate)
    {
        this.lastBuildDate = lastBuildDate;
    }

    public ArrayList<Item> getItem ()
    {
        return items;
    }

    public void setItem (Item item)
    {
        items.add(item);
    }

    public Image getImage ()
    {
        return image;
    }

    public void setImage (Image image)
    {
        this.image = image;
    }

    public String getLanguage ()
    {
        return language;
    }

    public void setLanguage (String language)
    {
        this.language = language;
    }

    public String getCopyright ()
    {
        return copyright;
    }

    public void setCopyright (String copyright)
    {
        this.copyright = copyright;
    }

    public String getWebMaster ()
    {
        return webMaster;
    }

    public void setWebMaster (String webMaster)
    {
        this.webMaster = webMaster;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [pubDate = "+pubDate+", title = "+title+", managingEditor = "+managingEditor+", description = "+description+", link = "+link+", lastBuildDate = "+lastBuildDate+", items = "+items+", image = "+image+", language = "+language+", copyright = "+copyright+", webMaster = "+webMaster+"]";
    }
}
