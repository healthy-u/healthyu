package com.example.dustin.cs495helloworld;

import android.content.Context;
import android.database.sqlite.*;
import android.provider.BaseColumns;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by dustin on 3/31/17.
 */

public class Database extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "healthyu.db";
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    public static String dateToSqlString(Date date) {
        if (date == null) return null;
        else return dateFormat.format(date);
    }

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Tables.UserTable.CREATE_TABLE_STATEMENT);
        db.execSQL(Tables.RunTable.CREATE_TABLE_STATEMENT);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(Tables.UserTable.DROP_TABLE_STATEMENT);
        db.execSQL(Tables.RunTable.DROP_TABLE_STATEMENT);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
