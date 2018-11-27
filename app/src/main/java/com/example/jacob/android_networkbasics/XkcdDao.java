package com.example.jacob.android_networkbasics;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

public class XkcdDao {
    private final static String baseUrl = "https://xkcd.com/";
    private final static String endUrl = "info.0.json";
    private final static String recentUrl = baseUrl + endUrl;
    private final static String specificUrl = baseUrl + "%d/" + endUrl;


    private static XkcdComic getComic(String urlString) {
        String url = NetworkAdapter.httpRequest(urlString);
        XkcdComic comic = null;
        try {
            JSONObject comicAsJson = new JSONObject(url);
            comic = new XkcdComic(comicAsJson);


            String imageUrl = null;
            imageUrl = comic.getImg();
            if (imageUrl != null) {
                Bitmap bitmap;
                bitmap = NetworkAdapter.httpImageRequest(imageUrl);
                if (bitmap != null) {
                    comic.setBitmap(bitmap);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
            return comic;
        }
        return comic;
    }


}
