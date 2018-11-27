package com.example.joshh.android_networkbasics;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

public class XkcdDao {
    private final static String BASE_URL = "https://xkcd.com/";
    private final static String URL_ENDING = "info.0.json";
    private final static String RECENT_COMIC = BASE_URL + URL_ENDING;
    private final static String SPECIFIC_COMIC = BASE_URL + "%d/" + URL_ENDING;

    public static int maxComicNumber;

    private static XkcdComic getComic(String url){
        XkcdComic xkcdComic = null;
        try {
            JSONObject jsonObject = new JSONObject(NetworkAdapter.httpRequest(url, ""));
            xkcdComic = new XkcdComic(jsonObject);
            Bitmap imageBitMap = NetworkAdapter.httpImageRequest(url);
            xkcdComic.setImg(imageBitMap.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return xkcdComic;
    }

    public static XkcdComic getRecentComic(){
        XkcdComic xkcdComic = getComic(RECENT_COMIC);
        maxComicNumber = xkcdComic.getNum();
        return xkcdComic;
    }

    public static XkcdComic getNextComic(XkcdComic xkcdComic){
        int nextComic = xkcdComic.getNum() + 1;
        String newUrl = String.format(SPECIFIC_COMIC, nextComic);
        return getComic(newUrl);
    }

    public static XkcdComic getPreviousComic(XkcdComic xkcdComic){
        int previousComic = xkcdComic.getNum() - 1;
        String newUrl = String.format(SPECIFIC_COMIC, previousComic);
        return getComic(newUrl);
    }

    public static XkcdComic getRandomComic(){
        int randomComicNum = ((int)(Math.random() * maxComicNumber) + 1);
        String newUrl = String.format(SPECIFIC_COMIC, randomComicNum);
        return getComic(newUrl);
    }

}
