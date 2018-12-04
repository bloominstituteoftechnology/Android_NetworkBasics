package com.thadocizn.networkbasics;

import android.app.Notification;
import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
public class XkcdDao {
    private final static String BASE_URL = "https://xkcd.com/";
    private final static String URL_ENDING = "info.0.json";
    private final static String RECENT_COMIC = BASE_URL + URL_ENDING;
    private final static String SPECIFIC_COMIC = BASE_URL + "%d/" + URL_ENDING;
    public static int maxComicNumber;

    private static XkcdComic getComic(String url){
        XkcdComic comic = null;
        try {
            JSONObject jsonObject = new JSONObject(NetworkAdapter.httpRequest(url));
            comic = new XkcdComic(jsonObject);
            Bitmap bitmap = NetworkAdapter.httpImageRequest(comic.getImg());
            comic.setImage(bitmap);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return comic;
    }

    public static XkcdComic getRecentComic(){
        XkcdComic comic = getComic(RECENT_COMIC);
        maxComicNumber = comic.getNum();
        return comic;
    }

    public static XkcdComic getNextComic(XkcdComic comic){
        int next = comic.getNum() + 1;
        String url = SPECIFIC_COMIC.replace("%d", Integer.toString(next));
        return getComic(url);
    }

    public static XkcdComic getPreviousComic(XkcdComic comic){
        int previous = comic.getNum() - 1;
        String url = SPECIFIC_COMIC.replace("%d", Integer.toString(previous));
        return getComic(url);
    }

    public static XkcdComic getRandomComic(){
        int random = ((int)(Math.random() * maxComicNumber) + 1);
        String url = SPECIFIC_COMIC.replace("%d", Integer.toString(random));
        return getComic(url);
    }

}
