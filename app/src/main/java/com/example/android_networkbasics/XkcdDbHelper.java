package com.example.android_networkbasics;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class XkcdDbHelper extends SQLiteOpenHelper {

    private static final int version = 1;

    public XkcdDbHelper(Context context) {
        super (context, XkcdDbContract.ComicEntry.TABLE_NAME, null, version);
    }

    public XkcdDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        this(context);
    }

    public XkcdDbHelper( Context context,  String name,  SQLiteDatabase.CursorFactory factory, int version,  DatabaseErrorHandler errorHandler) {
        this(context);
    }

    public XkcdDbHelper( Context context,  String name, int version, SQLiteDatabase.OpenParams openParams) {
        this(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(XkcdDbContract.ComicEntry.SQL_CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(XkcdDbContract.ComicEntry.SQL_DELETE_TABLE);

    }
}
