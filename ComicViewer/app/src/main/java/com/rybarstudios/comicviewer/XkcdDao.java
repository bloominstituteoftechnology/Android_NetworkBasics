package com.rybarstudios.comicviewer;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

public class XkcdDao {
    public static final String BASE_URL = "https://xkcd.com/";
    public static final String URL_ENDING = "info.0.json";
    public static final String MOST_RECENT_COMIC = BASE_URL + URL_ENDING;
    public static final String SPECIFIC_COMIC = BASE_URL + "%d/" + URL_ENDING;

    public static int maxComicNumber;

    private static XkcdComic getComic(String stringUrl) {
        XkcdComic xkcdComic = null;

        try {
            JSONObject jsonObject = new JSONObject(NetworkAdapter.httpGetRequest(stringUrl));
            xkcdComic = new XkcdComic(jsonObject);
            Bitmap image = NetworkAdapter.httpImageRequest(xkcdComic.getImg());
            xkcdComic.setBitMap(image);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return xkcdComic;
    }

    public static XkcdComic getRecentComic() {
        XkcdComic xkcdComic = getComic(MOST_RECENT_COMIC);
        maxComicNumber = xkcdComic.getNum();
        return xkcdComic;
    }

    public static XkcdComic getNextComic(XkcdComic comic) {
        int num = comic.getNum() + 1;
        String newComic = String.format(SPECIFIC_COMIC, num);
        return getComic(newComic);
    }

    public static XkcdComic getPreviousComic(XkcdComic comic) {
        int num = comic.getNum() - 1;
        String newComic = String.format(SPECIFIC_COMIC, num);
        return getComic(newComic);
    }

    public static XkcdComic getRandomComic(){
        int randomComicNum = ((int)(Math.random() * maxComicNumber) + 1);
        String newUrl = String.format(SPECIFIC_COMIC, randomComicNum);
        return getComic(newUrl);
    }

}
