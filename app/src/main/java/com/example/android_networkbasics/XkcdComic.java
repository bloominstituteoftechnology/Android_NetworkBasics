package com.example.android_networkbasics;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

public class XkcdComic {

    public XkcdComic(JSONObject jsonObject) {
        try {
            month = jsonObject.getString("month");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            num = jsonObject.getInt("num");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            link = jsonObject.getString("link");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            year = jsonObject.getString("year");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            news = jsonObject.getString("news");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            safe_title = jsonObject.getString("safe_title");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            transcript = jsonObject.getString("transcript");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            alt = jsonObject.getString("alt =");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            img = jsonObject.getString("img");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            title = jsonObject.getString("title");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            day = jsonObject.getString("day");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String month,link,year,news,safe_title,transcript,alt,img,title,day;
    private int num;

    private Bitmap bitmap;

    public String getMonth() {
        return month;
    }

    public int getNum() {
        return num;
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

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}