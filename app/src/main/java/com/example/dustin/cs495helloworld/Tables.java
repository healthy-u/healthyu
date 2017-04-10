package com.example.dustin.cs495helloworld;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public final class Tables{
    private Tables() {}

    public static class UserTable implements BaseColumns {
        public static final String TABLE_NAME = "user";

        public static final String CREATE_TABLE_STATEMENT = "CREATE TABLE user ("
                + "user_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "user_firstname VARCHAR(20) NOT NULL,"
                + "user_lastname VARCHAR(20) NOT NULL,"
                + "user_username VARCHAR(20) NOT NULL,"
                + "user_email VARCHAR(50) NOT NULL,"
                + "user_password VARCHAR(70) NOT NULL,"
                + "lifetime_points DECIMAL(8, 3) DEFAULT 0 NOT NULL,"
                + "team_id MEDIUMINT DEFAULT NULL,"
                + "FOREIGN KEY(team_id) REFERENCES team(team_id))";

        public static final String DROP_TABLE_STATEMENT = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static final String[] columns = {"user_id", "user_firstname", "user_lastname", "user_username", "user_email", "lifetime_points", "team_id"};

        public static User findForUsernameAndPassword(SQLiteDatabase db, String username, String pass) {
            Cursor cursor = db.query(Tables.UserTable.TABLE_NAME, Tables.UserTable.columns, "user_username = ? and user_password = ?", new String[]{username, pass}, null, null, null);

            if (cursor.getCount() == 0) return null;
            else {

                cursor.moveToFirst();

                User u = selectFromCursor(cursor);

                cursor.close();

                return u;
            }
        }

        public static User findForID(SQLiteDatabase db, Long user_id) {
            Cursor cursor = db.rawQuery("select * from user where cast(user_id as text) = " + user_id + ";", null);

            if (cursor.getCount() == 0) return null;
            else {

                cursor.moveToFirst();

                User u = selectFromCursor(cursor);

                cursor.close();

                return u;
            }
        }

        static public User selectFromCursor(Cursor cursor) {
            return new User(
                    cursor.getLong(cursor.getColumnIndexOrThrow("user_id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("user_firstname")),
                    cursor.getString(cursor.getColumnIndexOrThrow("user_lastname")),
                    cursor.getString(cursor.getColumnIndexOrThrow("user_username")),
                    cursor.getString(cursor.getColumnIndexOrThrow("user_email")),
                    new BigDecimal(cursor.getFloat(cursor.getColumnIndexOrThrow("lifetime_points"))),
                    cursor.getLong(cursor.getColumnIndexOrThrow("team_id"))
            );
        }
    }

    public static class RunTable implements BaseColumns {

        public static final String TABLE_NAME = "run";

        public static final String[] columns = {"run_id", "user_id", "mile_count", "trail_coords", "start_time", "end_time"};

        public static final String DROP_TABLE_STATEMENT = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static final String CREATE_TABLE_STATEMENT = "CREATE TABLE run ("
                + "run_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "user_id INTEGER NOT NULL,"
                + "mile_count DECIMAL(2, 3) NOT NULL,"
                + "trail_coords VARCHAR(1000) DEFAULT NULL,"
                + "start_time DATETIME NOT NULL,"
                + "end_time DATETIME NOT NULL)";


        public static List<Run> findForUser(SQLiteDatabase db, User u) {
            Cursor cursor = db.rawQuery("select * from run where cast(user_id as text) = " + u.id + ";", null);


            if (cursor.getCount() == 0) return null;
            else {

                ArrayList<Run> runs  = new ArrayList<Run>();

                while (cursor.moveToNext()) {
                    runs.add(selectFromCursor(cursor));
                }

                cursor.close();

                return runs;
            }
        }

        public static Run selectFromCursor(Cursor cursor) {
            try {
                return new Run(
                        cursor.getLong(cursor.getColumnIndexOrThrow("run_id")),
                        cursor.getLong(cursor.getColumnIndexOrThrow("user_id")),
                        new BigDecimal(cursor.getFloat(cursor.getColumnIndexOrThrow("mile_count"))),
                        cursor.getString(cursor.getColumnIndexOrThrow("trail_coords")),
                        Database.dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow("start_time"))),
                        Database.dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow("end_time")))
                );
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

}