package com.example.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDataBaseHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "my_Library.db";
    public static final int DATABASE_VERSION = 2;

    public static final String TABLE_NAME = "my_Library";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "AUTHOR";
    public static final String COL_3 = "TITLE";
    public static final String COL_4 = "PAGES";

    public MyDataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, AUTHOR TEXT, TITLE TEXT, PAGES INTEGER)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(Book book) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, book.getTitle());
        contentValues.put(COL_3, book.getAuthor());
        contentValues.put(COL_4, book.getPages());

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

    public Cursor getAllData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }

    public boolean updateData(Book book) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, book.getId());
        contentValues.put(COL_2, book.getTitle());
        contentValues.put(COL_3, book.getAuthor());
        contentValues.put(COL_4, book.getPages());
        sqLiteDatabase.update(TABLE_NAME, contentValues, "ID = ?", new String[]{String.valueOf(book.getId())});
        return true;
    }

    public int deleteData(Book book) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, "ID = ?", new String[]{String.valueOf(book.getId())});
    }

    public void deleteAllData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + TABLE_NAME);

    }



}
