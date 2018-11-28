package com.example.jacob.android_networkbasics;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

public class XkcdComic {
    private String transcript;
    private String title;
    private String safe_title;
    private int num;
    private String news;
    private String alt;
    private String link;
    private String img;
    private String month;
    private String year;
    private String day;
    private Bitmap bitmap;


    public XkcdComic(JSONObject json) {
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
            this.num = Integer.parseInt(json.getString("num"));
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

        public String getTranscript () {
            return transcript;
        }

        public String getTitle () {
            return title;
        }

        public String getSafe_title () {
            return safe_title;
        }

        public int getNum () {
            return num;
        }

        public String getNews () {
            return news;
        }

        public String getAlt () {
            return alt;
        }

        public String getLink () {
            return link;
        }

        public String getImg () {
            return img;
        }

        public String getMonth () {
            return month;
        }

        public String getYear () {
            return year;
        }

        public String getDay () {
            return day;
        }

        public Bitmap getBitmap () {
            return bitmap;
        }

        public void setBitmap (Bitmap bitmap){
            this.bitmap = bitmap;
        }

        @Override
        public String toString () {
            return "ClassPojo [transcript = " + transcript + ", title = " + title + ", safe_title = " + safe_title + ", num = " + num + ", news = " + news + ", alt = " + alt + ", link = " + link + ", img = " + img + ", month = " + month + ", year = " + year + ", day = " + day + "]";
        }


    }