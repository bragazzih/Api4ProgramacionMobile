package com.example.tokengenerator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DbConfig extends SQLiteOpenHelper {

    public DbConfig(Context context) {
        super(context, "api.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USER_TOKEN_NAME(USER_TOKEN_NAME varchar(30) UNIQUE NOT NULL PRIMARY KEY)");
        db.execSQL("CREATE TABLE USER_TOKEN_CODE(TOKEN_NAME_ID varchar(30), " +
                "TOKEN_CODE varchar(64)," +
                "FOREIGN KEY (TOKEN_NAME_ID) REFERENCES USER_TOKEN_NAME(USER_TOKEN_NAME))");
        db.execSQL("COMMIT");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USER_TOKEN_NAME");
        db.execSQL("DROP TABLE IF EXISTS USER_TOKEN_CODE");
        db.execSQL("COMMIT");

    }

    public boolean insertTokenName(String tokenName) {
        SQLiteDatabase cursor = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USER_TOKEN_NAME", tokenName);
        long result = cursor.insert("USER_TOKEN_NAME", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertGeneratedToken(String associatedTokenName, String token ) {
        SQLiteDatabase cursor = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("TOKEN_NAME_ID", associatedTokenName);
        contentValues.put("TOKEN_CODE",token);

        long result = cursor.insert("USER_TOKEN_CODE", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<String> getAllTokenName() {
        ArrayList<String> tokenNames = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM USER_TOKEN_NAME";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.getCount() > 0) {
            while (c.moveToNext()) {
                String name = c.getString(c.getColumnIndexOrThrow("USER_TOKEN_NAME"));
                tokenNames.add(name);
            }

        }

        return tokenNames;

    }

}