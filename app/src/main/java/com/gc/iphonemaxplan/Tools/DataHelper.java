package com.gc.iphonemaxplan.Tools;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Record";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "table_name";
    private static final String DATA_NAME = "data_name";
    private static final String MONEY_NAME = "money_name";

    public DataHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + DATA_NAME + " VARCHAR, " + MONEY_NAME + " VARCHAR);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<String> getAllItem(String items) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> itme = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM '" + TABLE_NAME + "' order by data_name DESC;", null);
        while (cursor.moveToNext()) {
            itme.add(cursor.getString(cursor.getColumnIndex(items)));
        }
        cursor.close();
        db.close();
        return itme;
    }

    public String getDataName(String temp) {
        String dataName = null;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + DATA_NAME + " FROM " + TABLE_NAME + " WHERE " + DATA_NAME + " = '" + temp + "';", null);
        while (cursor.moveToNext()) {
            dataName = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return dataName;
    }

    public void insertinto(String DATA_NAME, String MONEY_NAME) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("insert into " + TABLE_NAME + " values ('" + DATA_NAME + "','" + MONEY_NAME + "');");
        db.close();
    }

    public void delete(String data_name) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + DATA_NAME + " = '" + data_name + "';");
        db.close();
    }

}
