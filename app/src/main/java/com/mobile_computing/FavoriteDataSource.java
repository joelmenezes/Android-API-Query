package com.mobile_computing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joel on 25-01-2017.
 */

public class FavoriteDataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbhelper;
    //add columns
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_TITLE, MySQLiteHelper.COLUMN_DATE,
            MySQLiteHelper.COLUMN_IMAGE, MySQLiteHelper.COLUMN_TEXT};

    public FavoriteDataSource(Context context){
        dbhelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbhelper.getWritableDatabase();
    }

    public void close(){
        dbhelper.close();
    }

    public Favorite createFavorite(String favorite){
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_TITLE, favorite);
        long insertID = database.insert(MySQLiteHelper.TABLE_FAVORITES, null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_FAVORITES, allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertID,
                null, null, null, null);
        cursor.moveToFirst();
        Favorite newFavorite = cursorToFavorite(cursor);
        cursor.close();
        return newFavorite;
    }

    public void deleteFavorite(Favorite favorite){
        long id = favorite.getId();
        System.out.println("Favorite deleted with id " + id);
        database.delete(MySQLiteHelper.TABLE_FAVORITES, MySQLiteHelper.COLUMN_ID + " = " + id, null);
    }

    public List<Favorite> getAllFavorites(){
        List<Favorite> favorites = new ArrayList<Favorite>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_FAVORITES, allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Favorite favorite = cursorToFavorite(cursor);
            favorites.add(favorite);
            cursor.moveToNext();
        }
        cursor.close();
        return favorites;
    }

    //add co
    private Favorite cursorToFavorite(Cursor cursor){
        Favorite favorite = new Favorite();
        favorite.setId(cursor.getLong(0));
        favorite.setTitle(cursor.getString(1));
        return favorite;
    }

}
