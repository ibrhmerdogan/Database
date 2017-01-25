package com.example.ibrhm.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ibrhm on 25.01.2017.
 */

public class databaseClass extends SQLiteOpenHelper {
    private static final String DATABASE_NAME   = "CallStateDB";
    // Contacts table name
    private static final String TABLE_COUNTRIES = "information";
    public databaseClass(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE information (id INTEGER PRIMARY KEY AUTOINCREMENT,isim TEXT,soyad TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE If EXIST information");
        onCreate(db);

    }
}
