package com.mobile_computing;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Joel on 25-01-2017.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    //table name
    public static final String TABLE_FAVORITES = "favorites";

    //columns
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_TEXT = "text";

    public static final String DATABASE_NAME = "favorites.db";
    public static final int DATABASE_VERSION = 1;

    //Database creation sql statement
    private static final String DATABASE_CREATE =
            "create table if not exists " + TABLE_FAVORITES +
                    "( " + COLUMN_ID + " integer primary key, " +
                    COLUMN_TITLE + " text, " + COLUMN_DATE + " text, "
            + COLUMN_IMAGE + " text, " + COLUMN_TEXT + " text)";

    public MySQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.w(MySQLiteHelper.class.getName(), "Upgrading database from version " + oldVersion + "to "
        + newVersion);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_FAVORITES);
        onCreate(db);
    }
}
