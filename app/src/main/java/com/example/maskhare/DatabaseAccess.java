package com.example.maskhare;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseAccess extends SQLiteAssetHelper {
    SQLiteDatabase db;

    public DatabaseAccess(Context context) {
        super(context, "mydb.db", null, 1);
        db = getWritableDatabase();
    }

    public SQLiteDatabase getDb() {
        return db;
    }

}
