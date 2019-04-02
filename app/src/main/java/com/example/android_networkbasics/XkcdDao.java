package com.example.android_networkbasics;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

public class XkcdDao {


        public static final String URL_BASE = "https://xkcd.com/";
        public static final String URL_ENDING = "info.0.json";
        public static final String RECENT_COMIC = URL_BASE + URL_ENDING;
        public static final int UNINITIALIZED = -1;
        public static int MIN_COMIC_NUM = 1;
        private static int maxComicNum = UNINITIALIZED;

        public static int getMaxComicNum() {
            return maxComicNum;
        }

        private static XkcdComic getComic(String urlStr) {
            String jsonStr = NetworkAdapter.httpRequest(urlStr);
            if (jsonStr == null) {
                return null;
            }

            try {
                JSONObject jsonObject = new JSONObject(jsonStr);
                XkcdComic xkcdComic = new XkcdComic(jsonObject);
                Bitmap bitmap = NetworkAdapter.httpImageRequest(xkcdComic.getImg());
                xkcdComic.setBitmap(bitmap);
                return xkcdComic;

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static XkcdComic getRecentComic() {
            XkcdComic recentComic = getComic(RECENT_COMIC);
            if (recentComic != null) {
                maxComicNum = recentComic.getNum();
            }
            return recentComic;
        }

        public static XkcdComic getPreviousComic(XkcdComic xkcdComic) {
            if (xkcdComic == null) {
                return null;
            }
            return getComic(URL_BASE + (xkcdComic.getNum() - 1) + "/" + URL_ENDING);
        }

        public static XkcdComic getNextComic(XkcdComic xkcdComic) {
            if (xkcdComic == null) {
                return null;
            }
            return getComic(URL_BASE + (xkcdComic.getNum() + 1) + "/" + URL_ENDING);
        }

        public static XkcdComic getRandomComic() {
            int randInt = (int)(Math.random() * maxComicNum);
            return getComic(URL_BASE + randInt + "/" + URL_ENDING);
        }
    }