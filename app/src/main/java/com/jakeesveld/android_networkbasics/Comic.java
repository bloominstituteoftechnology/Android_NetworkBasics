package com.jakeesveld.android_networkbasics;

import android.graphics.Bitmap;
import android.media.Image;

import org.json.JSONException;
import org.json.JSONObject;

public class Comic {
    private String title;
    private Bitmap image;
    private int id;
    private String date;

    public Comic(JSONObject json){
        try {
            this.title = json.getString("title");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.image = NetworkAdapter.httpImageRequest(json.getString("img"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.id = json.getInt("num");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            this.date = String.format("%s, %s, $s", json.getString("month"), json.getString("day"), json.getString("year"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public String getTitle() {
        return title;
    }

    public Bitmap getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }
}
