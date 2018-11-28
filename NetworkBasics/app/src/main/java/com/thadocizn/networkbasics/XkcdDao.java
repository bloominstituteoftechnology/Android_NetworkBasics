package com.thadocizn.networkbasics;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class XkcdDao {
    private static final String BASE_URL = "http://xkcd.com/";
    private static final String IMAGE_URL = "https://xkcd.com/info.0.json";
    private static final String SPECIFIC_COMIC = "https://xkcd.com/%d/info.0.json";
    private static XkcdComic current;
    private static final int MAX = 2077;

    private static XkcdComic getComic(String url){
        XkcdComic comic = null;
        try {
            String strUrl = NetworkAdapter.httpGetRequest(url);
            JSONObject jsComic = new JSONObject(strUrl);
            comic = new XkcdComic(jsComic);

            String urlString = comic.getImg();
            if (urlString != null){
                Bitmap bitmap;
                bitmap = NetworkAdapter.httpImageRequest(urlString);
                if (bitmap != null){
                    comic.setImage(bitmap);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        current = comic;
        return comic;
    }

    public static XkcdComic getRecentComic() {
        XkcdComic comic = null;
        comic = getComic(IMAGE_URL);
        return comic;
    }

    public static XkcdComic getNextComic() {
        XkcdComic comic = null;
        int num;
        num = current.getNum();
        if (num >= 0) {
            num ++ ;
            String url = SPECIFIC_COMIC.replace("%d/", Integer.toString(num));
            comic = getComic(url);
        }
        return comic;
    }

    public static XkcdComic getPreviousComic() {
        XkcdComic comic = null;
        int num = current.getNum();
        if (num > 0) {
            num --;
            String url = SPECIFIC_COMIC.replace("%d/", Integer.toString(num));
            comic = getComic(url);
        }
        return comic;
    }

    public static XkcdComic getRandomComic(){
        XkcdComic comic = null;
        String randomNumber = String.valueOf((Math.round(Math.random()* MAX + 1)));
        String url = SPECIFIC_COMIC.replace("%d/", randomNumber);
        comic = getComic(url);
        return comic;
    }
}
