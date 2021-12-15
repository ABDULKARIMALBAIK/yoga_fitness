package com.example.asus.androidyogafitness.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class YogaDB extends SQLiteAssetHelper {

    private static final String DB_NAME = "yoga.db";
    private static final int DB_VER = 1;

    public YogaDB(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public int getSettingMode() {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Mode"};
        String sqlTable = "Settings";

        qb.setTables(sqlTable);
        Cursor cursor = db.query(sqlTable, sqlSelect, null, null, null, null, null);
        cursor.moveToFirst();

        return cursor.getInt(cursor.getColumnIndex("Mode"));
    }

    public void saveSettingsMode(int value) {

        SQLiteDatabase db = getReadableDatabase();
        String query = "UPDATE Settings SET Mode = " + value;
        db.execSQL(query);
    }

    public List<String> getWorkoutDays() {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Day"};
        String sqlTable = "WorkoutDays";

        qb.setTables(sqlTable);
        Cursor cursor = db.query(sqlTable, sqlSelect, null, null, null, null, null);

        List<String> result = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){

            result.add(cursor.getString(cursor.getColumnIndex("Day")));
            cursor.moveToNext();
        }
        cursor.close();

        return result;
    }

    public void saveDay(String value) {

        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO WorkoutDays(Day) VALUES('%s');" , value);
        db.execSQL(query);
    }
}


