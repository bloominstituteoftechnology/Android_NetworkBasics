package com.example.android_networkbasics;

import org.json.JSONException;
import org.json.JSONObject;

public class XkcdDao {
    private static final String BASE_URL = "https://xkcd.com/";
    private static final String END_URL = "info.0.json";
    private static final String RECENT_COMIC = BASE_URL + END_URL;
    private static final String SPECIFIC_COMIC = BASE_URL + "%d/" + END_URL;
    public static int maxComicNumber;


    private static XkcdComic getComic(String url){

        try {
            JSONObject jsonObject = new JSONObject(NetworkAdapter.httpRequest(url));
            XkcdComic xkcdComic = new XkcdComic(jsonObject);
            xkcdComic.setBitmap(NetworkAdapter.httpImageRequest(xkcdComic.getImg()));
            return xkcdComic;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static XkcdComic getRecentComic() {

        XkcdComic xkcdComic = getComic(RECENT_COMIC);
        maxComicNumber = xkcdComic.getIndex();
        return xkcdComic;
    }

    public static XkcdComic getNextComic(XkcdComic xkcdComic) {
        int index = xkcdComic.getIndex();
        index++;
        return getComic(String.format(SPECIFIC_COMIC,index));
    }

    public static XkcdComic getPreviousComic(XkcdComic xkcdComic) {
        int index = xkcdComic.getIndex();
        index--;
        return getComic(String.format(SPECIFIC_COMIC,index));
    }

    static XkcdComic getRandomComic() {
        int index = (int)((Math.random()*maxComicNumber)+1);
        return getComic(String.format(SPECIFIC_COMIC,index));
    }

/*    private static String getUrl(int index){
        String result =  String.format("%s%d%s%s", BASE_URL, index, "/",END_URL);
        return result;
    }*/
}
