package com.example.earthdefensesystem.android_networkbasics;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

public class Comic
{
    private String transcript;
    private String title;
    private String safe_title;
    private String num;
    private String news;
    private String alt;
    private String link;
    private String img;
    private String month;
    private String year;
    private String day;
    private Bitmap comic;

    public Bitmap getComic() {
        return comic;
    }

    public void setComic(Bitmap comic) {
        this.comic = comic;
    }

    public Comic(JSONObject json) {
        try {
            this.transcript = json.getString("transcript");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.title = json.getString("title");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.safe_title = json.getString("safe_title");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.num = json.getString("num");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.news = json.getString("news");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.alt = json.getString("alt");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.link = json.getString("link");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.img = json.getString("img");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.month = json.getString("month");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.year = json.getString("year");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.day = json.getString("day");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getTranscript ()
    {
        return transcript;
    }

    public void setTranscript (String transcript)
    {
        this.transcript = transcript;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getSafe_title ()
    {
        return safe_title;
    }

    public void setSafe_title (String safe_title)
    {
        this.safe_title = safe_title;
    }

    public String getNum ()
    {
        return num;
    }

    public void setNum (String num)
    {
        this.num = num;
    }

    public String getNews ()
    {
        return news;
    }

    public void setNews (String news)
    {
        this.news = news;
    }

    public String getAlt ()
    {
        return alt;
    }

    public void setAlt (String alt)
    {
        this.alt = alt;
    }

    public String getLink ()
    {
        return link;
    }

    public void setLink (String link)
    {
        this.link = link;
    }

    public String getImg ()
    {
        return img;
    }

    public void setImg (String img)
    {
        this.img = img;
    }

    public String getMonth ()
    {
        return month;
    }

    public void setMonth (String month)
    {
        this.month = month;
    }

    public String getYear ()
    {
        return year;
    }

    public void setYear (String year)
    {
        this.year = year;
    }

    public String getDay ()
    {
        return day;
    }

    public void setDay (String day)
    {
        this.day = day;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [transcript = "+transcript+", title = "+title+", safe_title = "+safe_title+", num = "+num+", news = "+news+", alt = "+alt+", link = "+link+", img = "+img+", month = "+month+", year = "+year+", day = "+day+"]";
    }
}

