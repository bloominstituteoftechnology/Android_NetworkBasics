package com.thadocizn.networkbasics;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class XkcdDao {
    private static final String BASE_URL = "http://xkcd.com/";
    private static final String IMAGE_URL = "https://xkcd.com/info.0.json";
    private static final String RECENT_COMIC = "https://xkcd.com/%d/info.0.json";

    private static XkcdComic getComic(String url){
        XkcdComic comic = null;
        try {
            String strUrl = NetworkAdapter.httpGetRequest(url);
            JSONObject jsComic = new JSONObject(strUrl);
            comic = new XkcdComic(jsComic);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return comic;
    }
}
