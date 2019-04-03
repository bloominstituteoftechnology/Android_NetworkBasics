package com.lambdaschool.android_networkbasics;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

public class XkcdComic {
    private String news;
    private String img;
    private String transcript;
    private String month;
    private String year;
    private String num;
    private String link;
    private String alt;
    private String title;
    private String day;
    private String safe_title;
    private Bitmap bitmap;

    public XkcdComic(JSONObject json) {
        try {
            this.news = json.getString("news");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.img = json.getString("img");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.transcript = json.getString("transcript");
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
            this.num = json.getString("num");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.link = json.getString("link");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.alt = json.getString("alt");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.title = json.getString("title");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.day = json.getString("day");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.safe_title = json.getString("safe_title");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getSafe_title() {
        return safe_title;
    }

    public void setSafe_title(String safe_title) {
        this.safe_title = safe_title;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public String toString() {
        return "[title = " + title + ", news = " + news + ", img = " + img + ", month = " + month + ", year = " + year + ", num = " + num + ", link = " + link + ", alt = " + alt + ", day = " + day + "]";
    }
}

