package com.example.android_networkbasics;

import android.provider.BaseColumns;

public class XkcdDbContract {

    public class ComicEntry implements BaseColumns {
        public static final String TABLE_NAME = "comics";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_LAST_READ = "last_read";
        public static final String COLUMN_NAME_FAVORTIE = "favorite";



        public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " ( " +
                COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_LAST_READ + " INTEGER," +
                COLUMN_NAME_FAVORTIE + " INTEGER" +
                " );";

        public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
