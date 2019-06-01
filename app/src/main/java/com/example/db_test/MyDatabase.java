package com.example.db_test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME  = "X.db";
    private static final String TABLE_B = "WEB";
    //private static final String COL_ID ="ID";
    //private static final String COL_ACC = "ACCOUNT";
    //private static final String COL_PW = "PASSWORD";
    private static final String COL_NAME = "WAB_NAME";
    private static final String COL_URL = "WAB_URL";
    private static final String COL_INFO = "WAB_INFO";

    MyDatabase(Context context)
    {
        super(context,DATABASE_NAME ,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.beginTransaction();
        try {
            sqLiteDatabase.execSQL(
                    "CREATE TABLE " + TABLE_B
                            + "(" + COL_NAME + " TEXT,"
                            + COL_URL + " TEXT)"
            );
            sqLiteDatabase.setTransactionSuccessful();
        }finally {
            sqLiteDatabase.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void AddNewData(WebData wd)
    {
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(COL_NAME,wd.getName());
            values.put(COL_URL,wd.getUrl());
            db.insert(TABLE_B,null,values);
        }finally {
            if(db != null) db.close();
        }
    }

    public ArrayList<WebData> getAllData()
    {
        ArrayList<WebData> allData = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cur = null;
        try{
            cur = db.query(TABLE_B,new String[]{COL_NAME,COL_URL},null,null,null,null,null,"500");
            if(cur!=null){
                while(cur.moveToNext()){
                    WebData wd  =new WebData(
                            cur.getString(cur.getColumnIndex(COL_NAME))
                            ,""
                            ,cur.getString(cur.getColumnIndex(COL_URL)));
                    allData.add(wd);
                }
            }
        }finally {
            if(cur!=null) cur.close();
        }
        db.close();
        return allData;
    }

    public void DeleteData(String key)
    {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.delete(TABLE_B, COL_NAME + " = ?", new String[] { key});
        } finally {
            if(db != null) db.close();
        }
    }
}
