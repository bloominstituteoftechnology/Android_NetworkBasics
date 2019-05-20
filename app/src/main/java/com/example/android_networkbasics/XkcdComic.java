package com.example.android_networkbasics;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class XkcdComic {
private int index;
private String link, transcript, img, title;
private Bitmap bitmap;

    public XkcdComic(JSONObject jsonObject) {
        try {
            this.link = jsonObject.getString("link");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.transcript = jsonObject.getString("transcript");
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
            this.index = jsonObject.getInt("num");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getIndex() {
        return index;
    }

    public String getLink() {
        return link;
    }

    public String getTranscript() {
        return transcript;
    }

    public String getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
    /*    {
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
}
