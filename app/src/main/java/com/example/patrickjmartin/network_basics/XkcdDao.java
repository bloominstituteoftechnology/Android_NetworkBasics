package com.example.patrickjmartin.network_basics;

public class XkcdDao {

    private final static String BASE_URL = "https://xkcd.com/";
    private final static String URL_ENDING = "info.0.json";
    private final static String RECENT_COMIC = BASE_URL + URL_ENDING;
    private final static String SPECIFIC_COMIC = BASE_URL + "%d/" + URL_ENDING;

    public static XkcdComic getRecentComic() {
        XkcdComic xkcdComic = null;

        //stuff

        return xkcdComic;
    }

    public static XkcdComic getRandomComic() {
        XkcdComic xkcdComic = null;

        //stuff

        return xkcdComic;
    }

    public static XkcdComic getNextComic() {
        XkcdComic xkcdComic = null;

        //stuff

        return xkcdComic;
    }

    public static XkcdComic getPreviousComic() {
        XkcdComic xkcdComic = null;

        //stuff

        return xkcdComic;
    }
}
