package com.example.android_networkbasics;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

public class XkcdComic {
    JSONObject jsonObject;
    Bitmap urlImage;
    String imageUrl,stringId;

    public void XkcdComic(JSONObject jsonObject){
        this.jsonObject = jsonObject;
        try {
            this.imageUrl = jsonObject.getString("img");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.urlImage = NetworkAdapter.httpImageRequest(imageUrl);
        try {
            this.stringId = jsonObject.getString("num");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public Bitmap getUrlImage() {
        return urlImage;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getStringId() {
        return stringId;
    }
}
