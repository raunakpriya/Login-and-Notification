package com.example.demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Members1.db";
    public static final String TABLE_NAME = "members_data_1";
    public static final String COL1 = "UserName";
    public static final String COL2 = "Password";
    public static final String COL3 = "email";
    public static final String COL4 = "mobile";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(UserName Text Primary Key, Password Text,email Text, mobile Number)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String UserName, String Password, String email, String mobile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, UserName);
        contentValues.put(COL2, Password);
        contentValues.put(COL3, email);
        contentValues.put(COL4, mobile);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result==-1)
            return false;
        else
            return true;

    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from " +TABLE_NAME,null);
        return res;
    }
    public boolean updatedata(String Password, String UserName, String email, String mobile){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, UserName);
        contentValues.put(COL2, Password);
        contentValues.put(COL3, email);
        contentValues.put(COL4, mobile);
        db.update(TABLE_NAME,contentValues,"UserName= ?", new String[] {UserName} );
        return true;




    }



}
