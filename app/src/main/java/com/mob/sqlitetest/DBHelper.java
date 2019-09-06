package com.mob.sqlitetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    private static String DATAVASE_NAME = "";

    public DBHelper(Context context, String dbname) {

        super(context, dbname, null, DATABASE_VERSION);
        DATAVASE_NAME = dbname;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if(DATAVASE_NAME.equals("accusation.db")){
            db.execSQL("CREATE TABLE if not exists accusation (_id INTEGER PRIMARY KEY AUTOINCREMENT, number TEXT);");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == DATABASE_VERSION) {
            db.execSQL("drop table accusation");
            onCreate(db);
        }
    }
}
