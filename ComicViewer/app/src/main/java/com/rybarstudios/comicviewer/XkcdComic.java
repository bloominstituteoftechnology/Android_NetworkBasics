package com.rybarstudios.comicviewer;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

public class XkcdComic {

 /*   {
        "month": "4",
            "num": 2131,
            "link": "https://xkcd.com/2131/emojidome_bracket_round_of_128.png",
            "year": "2019",
            "news": "",
            "safe_title": "Emojidome",
            "transcript": "",
            "alt": "Thank you to the xkcd April 1st volunteers/commentators, including @Chromakode, Kevin, @Aiiane, Patrick, Kat, Reuven, @cotrone, @bstaffin, @zigdon, schwal, Stereo, and everyone who voted!",
            "img": "https://imgs.xkcd.com/comics/emojidome.png",
            "title": "Emojidome",
            "day": "1"
    }*/

    private String month, link, year, news, safeTitle, transcript, alt, img, title, day;
    private int num;
    private Bitmap bitMap;

    public XkcdComic(JSONObject jsonObject) {
        try {
            this.month = jsonObject.getString("month");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.link = jsonObject.getString("link");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.year = jsonObject.getString("year");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.news = jsonObject.getString("news");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.safeTitle = jsonObject.getString("safe_title");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.transcript = jsonObject.getString("transcript");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.alt = jsonObject.getString("alt");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.img = jsonObject.getString("img");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.title = jsonObject.getString("title");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.day = jsonObject.getString("day");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.num = jsonObject.getInt("num");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getMonth() {
        return month;
    }

    public String getLink() {
        return link;
    }

    public String getYear() {
        return year;
    }

    public String getNews() {
        return news;
    }

    public String getSafeTitle() {
        return safeTitle;
    }

    public String getTranscript() {
        return transcript;
    }

    public String getAlt() {
        return alt;
    }

    public String getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    public String getDay() {
        return day;
    }

    public int getNum() {
        return num;
    }

    public Bitmap getBitMap() {
        return bitMap;
    }

    public void setBitMap(Bitmap bitMap) {
        this.bitMap = bitMap;
    }
}
