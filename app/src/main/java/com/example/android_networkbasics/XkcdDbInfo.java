package com.example.android_networkbasics;

public class XkcdDbInfo {
    private int id;
    private int lastRead;
    private boolean favorite;

    public XkcdDbInfo(int id, int lastRead, boolean favorite) {
        this.id = id;
        this.lastRead = lastRead;
        this.favorite = favorite;
    }

    public int getId() {
        return id;
    }

    public int getLastRead() {
        return lastRead;
    }

    public boolean isFavorite() {
        return favorite;
    }
}
