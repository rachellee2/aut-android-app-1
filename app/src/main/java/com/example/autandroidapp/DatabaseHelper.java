package com.example.autandroidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "courses.db";
    public static final String TABLE_NAME = "courses_table";
    public static final String COL_1 = "coursename";
    public static final String COL_2 = "coursecode";
    public static final String COL_3 = "courseday";
    public static final String COL_4 = "courselocation";
    public static final String COL_5 = "coursetime";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (COURSENAME TEXT,COURSECODE TEXT, COURSEDAY TEXT, COURSELOCATION TEXT,COURSETIME INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name,String code, String day, String location, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,code);
        contentValues.put(COL_3,day);
        contentValues.put(COL_4,location);
        contentValues.put(COL_5,time);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from "+TABLE_NAME ;
        Cursor data = db.rawQuery(query, null);
        return data;

    }


    public boolean updateData(String name, String code, String day, String location, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,code);
        contentValues.put(COL_3,day);
        contentValues.put(COL_4,location);
        contentValues.put(COL_5,time);
        db.update(TABLE_NAME, contentValues, "Name = ?",new String[] { name });
        return true;
    }

    public Integer deleteData (String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "Name = ?",new String[] {name});
    }
}