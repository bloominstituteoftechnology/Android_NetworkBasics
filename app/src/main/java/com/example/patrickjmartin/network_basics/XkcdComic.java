package com.example.patrickjmartin.network_basics;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

public class XkcdComic {

    private String month, link, year, news, safe_title, transcript, alt, img, title, day;
    private int num;
    private Bitmap bitMap;

    public XkcdComic(JSONObject json) {
        try {
            this.month = json.getString("month");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            this.link = json.getString("link");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            this.year = json.getString("year");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            this.news = json.getString("news");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            this.safe_title = json.getString("safe_title");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            this.transcript = json.getString("transcript");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            this.alt = json.getString("alt");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            this.img = json.getString("img");
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
            this.num = json.getInt("num");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getMonth() {
        return month;
    }

    public String getLink() {
        return link;
    }

    public String getYear() {
        return year;
    }

    public String getNews() {
        return news;
    }

    public String getSafe_title() {
        return safe_title;
    }

    public String getTranscript() {
        return transcript;
    }

    public String getAlt() {
        return alt;
    }

    public String getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    public String getDay() {
        return day;
    }

    public int getNum() {
        return num;
    }

    public Bitmap getBitMap() {
        return bitMap;
    }

    public void setBitMap(Bitmap bitMap) {
        this.bitMap = bitMap;
    }
}
