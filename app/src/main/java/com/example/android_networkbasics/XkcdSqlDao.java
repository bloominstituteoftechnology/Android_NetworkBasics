package com.example.android_networkbasics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class XkcdSqlDao {
    private static SQLiteDatabase db;

    private void initializeInstance(Context context) {
        if (db == null) {
            XkcdDbHelper xkcdDbHelper = new XkcdDbHelper(context);
            db = xkcdDbHelper.getWritableDatabase();
        }
    }

    public XkcdSqlDao(Context context) {
        initializeInstance(context);
    }

    public XkcdDbInfo getComic(int i) {
        final Cursor cursor = db.rawQuery("SELECT * FROM " + XkcdDbContract.ComicEntry.TABLE_NAME + " WHERE ID=?", new String[]{Integer.toString(i)});
        cursor.moveToFirst();

        int index = cursor.getColumnIndexOrThrow(XkcdDbContract.ComicEntry.COLUMN_NAME_ID);
        int id = cursor.getInt(index);

        index = cursor.getColumnIndexOrThrow(XkcdDbContract.ComicEntry.COLUMN_NAME_LAST_READ);
        int lastRead = cursor.getInt(index);

        index = cursor.getColumnIndexOrThrow(XkcdDbContract.ComicEntry.COLUMN_NAME_FAVORTIE);
        boolean favorite = "1".equals(String.valueOf(cursor.getInt(index)));

        cursor.close();

        return new XkcdDbInfo(id, lastRead, favorite);
    }

    public int updateComic(XkcdDbInfo xkcdDbInfo) {
        int affectedRows = db.update(
                XkcdDbContract.ComicEntry.TABLE_NAME,
                getContentValues(xkcdDbInfo),
                XkcdDbContract.ComicEntry.COLUMN_NAME_ID+ "=?",
                new String[]{Integer.toString(xkcdDbInfo.getId())}
        );
        return affectedRows;
    }

    public XkcdDbInfo getComic(Cursor cursor) {
        int index;

        index = cursor.getColumnIndexOrThrow(XkcdDbContract.ComicEntry.COLUMN_NAME_ID);
        int id = cursor.getInt(index);

        index = cursor.getColumnIndexOrThrow(XkcdDbContract.ComicEntry.COLUMN_NAME_LAST_READ);
        int lastRead = cursor.getInt(index);

        index = cursor.getColumnIndexOrThrow(XkcdDbContract.ComicEntry.COLUMN_NAME_FAVORTIE);
        boolean favorite = "1".equals(String.valueOf(cursor.getInt(index)));

        return new XkcdDbInfo(id, lastRead, favorite);
    }


    public long createData(XkcdDbInfo xkcdDbInfo) {
        ContentValues values = getContentValues(xkcdDbInfo);

        final long insert = db.insert(XkcdDbContract.ComicEntry.TABLE_NAME, null, values);
        return insert;
    }


    public List<XkcdDbInfo> getAllDatas() {
        final Cursor cursor = db.rawQuery("SELECT * FROM " + XkcdDbContract.ComicEntry.TABLE_NAME, new String[]{});

        List<XkcdDbInfo> rows = new ArrayList<>();
        while (cursor.moveToNext()) {
            rows.add(getComic(cursor));
        }

        cursor.close();
        return rows;
    }

    private ContentValues getContentValues(XkcdDbInfo xkcdDbInfo) {
        ContentValues values = new ContentValues();

        values.put(XkcdDbContract.ComicEntry.COLUMN_NAME_ID, xkcdDbInfo.getId());
        values.put(XkcdDbContract.ComicEntry.COLUMN_NAME_LAST_READ, xkcdDbInfo.getLastRead());
        values.put(XkcdDbContract.ComicEntry.COLUMN_NAME_FAVORTIE, xkcdDbInfo.isFavorite());

        return values;
    }
}
