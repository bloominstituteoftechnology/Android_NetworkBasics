package com.vivekvishwanath.android_networkbasics;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

public class XkcdComic {

    private int num;
    private String link, news, safe_title, transcript,
            alt, img, title, month, year, day;
    private Bitmap image;

    public XkcdComic(JSONObject jsonObject) {
        try {
            link = jsonObject.getString("link");
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
            alt = jsonObject.getString("alt");
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
            month = jsonObject.getString("month");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            year = jsonObject.getString("year");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            day = jsonObject.getString("day");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            num = jsonObject.getInt("num");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getNum() {
        return num;
    }

    public String getLink() {
        return link;
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

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getDay() {
        return day;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}



