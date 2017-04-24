package com.example.dustin.cs495helloworld;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by dustin on 4/1/17.
 */

public class Run {
    long id = 0L;
    long user_id;
    BigDecimal miles = BigDecimal.ZERO;
    String trailCoords = null;
    Date startTime;
    Date endTime = null;

    public Run(long id, long user_id, BigDecimal miles, String trailCoords, Date startTime, Date endTime) {
        this.id = id;
        this.user_id = user_id;
        this.miles = miles;
        this.trailCoords = trailCoords;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Run(long user_id, Date startTime) {
        this(0L, user_id, BigDecimal.ZERO, null, startTime, null);
    }

    public Run create(SQLiteDatabase db) {

        Date dbEndTime = endTime;
        if (dbEndTime == null) dbEndTime = new Date();

        ContentValues values = new ContentValues();
        values.put("user_id", this.user_id);
        values.put("mile_count", this.miles.toString());
        values.put("trail_coords", this.trailCoords);
        values.put("start_time", Database.dateToSqlString(startTime));
        values.put("end_time", Database.dateToSqlString(dbEndTime));

        long newId = db.insert("run", null, values);
        this.id = newId;
        return this;
    }

    @Override
    public String toString() {
        return this.startTime.toString() + "-" + this.endTime.toString() + ": " + miles + "mi.";
    }
}
