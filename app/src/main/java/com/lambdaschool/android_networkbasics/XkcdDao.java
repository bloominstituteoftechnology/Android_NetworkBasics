package com.lambdaschool.android_networkbasics;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import static java.lang.Math.random;

public class XkcdDao {
    private static final String URL_BASE = "https://xkcd.com/";
    private static final String URL_ENDING = "info.0.json";
    private static final String URL_RECENT = "https://xkcd.com/info.0.json";
    private static final String URL_SPECIFIC = "https://xkcd.com/%d/info.0.json";
    public static int maxComicNumber;

    private static XkcdComic getComic(String url) {
        XkcdComic xkcdComic = null;
        try {
            JSONObject json = new JSONObject(NetworkAdapter.httpRequest(url));
            xkcdComic = new XkcdComic(json);
            Bitmap bitmap = NetworkAdapter.httpImageRequest(xkcdComic.getImg());
            xkcdComic.setBitmap(bitmap);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return xkcdComic;
    }

    public static XkcdComic getRecentComic() {
        XkcdComic xkcdComic = getComic(URL_RECENT);
        maxComicNumber = Integer.valueOf(xkcdComic.getNum());

        return xkcdComic;
    }

    public static XkcdComic getNextComic(XkcdComic xkcdComic) {
        int num = Integer.valueOf(xkcdComic.getNum()) + 1;

        return getComic(String.format(Locale.US, URL_SPECIFIC, num));
    }

    public static XkcdComic getPreviousComic(XkcdComic xkcdComic) {
        int num = Integer.valueOf(xkcdComic.getNum()) - 1;

        return getComic(String.format(Locale.US, URL_SPECIFIC, num));
    }

    public static XkcdComic getRandomComic() {
        int randomNum = (int) ((Math.random() * maxComicNumber) + 1);

        return getComic(String.format(Locale.US, URL_SPECIFIC, randomNum));
    }
}
