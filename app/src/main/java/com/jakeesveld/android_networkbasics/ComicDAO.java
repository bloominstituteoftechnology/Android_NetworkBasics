package com.jakeesveld.android_networkbasics;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

public class ComicDAO {

    private static final String URL_PREFIX = "https://xkcd.com/";
    private static final String URL_SUFFIX = "info.0.json";

    public static Comic getLatest(){
        String result = NetworkAdapter.httpRequest(URL_PREFIX + URL_SUFFIX);
        Comic latestComic;

        try{
            JSONObject json = new JSONObject(result);
            latestComic = new Comic(json);

        } catch (JSONException e) {
            e.printStackTrace();
            latestComic = null;
        }
        return latestComic;
    }

    public static Comic getPrevious(int id){
        Integer lastId = id - 1;
        String result = NetworkAdapter.httpRequest(URL_PREFIX + lastId.toString() + "/" + URL_SUFFIX);
        Comic previousComic;

        try{
            JSONObject json = new JSONObject(result);
            previousComic = new Comic(json);
        } catch (JSONException e) {
            e.printStackTrace();
            previousComic = null;
        }
        return previousComic;
    }

    public static Comic getNext(int id){
        Integer nextId = id + 1;
        String result = NetworkAdapter.httpRequest(URL_PREFIX + nextId + "/" + URL_SUFFIX);
        Comic nextComic;

        try{
            JSONObject json = new JSONObject(result);
            nextComic = new Comic(json);
        } catch (JSONException e) {
            e.printStackTrace();
            nextComic = null;
        }
        return nextComic;
    }

    public static Comic getRandom() {
        double doubRandomId = Math.random() * getLatest().getId();
        int intRandomId = (int) doubRandomId;
        Integer randomId = intRandomId + 1;
        String result = NetworkAdapter.httpRequest(URL_PREFIX + randomId + "/" + URL_SUFFIX);
        Comic randomComic;

        try {
            JSONObject json = new JSONObject(result);
            randomComic = new Comic(json);
        } catch (JSONException e) {
            e.printStackTrace();
            randomComic = null;
        }
        return randomComic;
    }
}
