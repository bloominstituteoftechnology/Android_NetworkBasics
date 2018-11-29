package com.thadocizn.networkbasics;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

public class XkcdComic {

    private  String month;
    private  int num;
    private  String link;
    private  String year;
    private  String news;
    private  String safeTitle;
    private  String transcript;
    private  String alt;
    private  String img;
    private  String title;
    private  String day;
    private Bitmap image;



    public String getMonth() {
        return month;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
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

    public String getSafeTitle() {
        return safeTitle;
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

    public XkcdComic(JSONObject jsonObject) {
        super();
        try {
            this.month = jsonObject.getString("month");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.num = jsonObject.getInt("num");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.link = jsonObject.getString("link");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.year = jsonObject.getString("year");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.news = jsonObject.getString("news");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.safeTitle = jsonObject.getString("safeTitle");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.transcript = jsonObject.getString("transcript");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.alt = jsonObject.getString("alt");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.img = jsonObject.getString("img");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.title = jsonObject.getString("title");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.day = jsonObject.getString("day");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}