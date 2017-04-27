package com.example.dustin.cs495helloworld;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dustin on 3/30/17.
 */

public class User {
    Long id = 0L;
    String firstname;
    String lastname;
    String username;
    String email;
    BigDecimal lifetime_points;
    List<Run> runs = new ArrayList<>();
    long team_id = 0L;

    public String fullname()
    {
        return firstname + " " + lastname;
    }

    public Run longestRun() {
        Collections.sort(runs);
        return runs.get(0);
    }

    public BigDecimal mostStepsPerDay() {
        BigDecimal mostMiles = new BigDecimal(0.0);
        String day = "0000-00-00";

        HashMap<String, BigDecimal> milesPerDay = new HashMap<String, BigDecimal>();

        for (int n = 0; n < runs.size(); n++) {
            Run current = runs.get(n);
            day = Tables.dateFormat.format(current.startTime);
            if (!milesPerDay.containsKey(day)) {
                milesPerDay.put(day, current.miles);
            }
            else {
                milesPerDay.put(day, current.miles.add(milesPerDay.get(day)));
            }
        }

        int index = 0;
        for (String key: milesPerDay.keySet()) {
            if (milesPerDay.get(key).compareTo(mostMiles) == 1) mostMiles = milesPerDay.get(key);
        }

        return mostMiles;
    }

    public User(Long ID, String first_name, String last_name, String user_name, String emailAddress, BigDecimal lifetimePoints, Long teamId) {
        id = ID;
        firstname = first_name;
        lastname = last_name;
        username = user_name;
        email = emailAddress;
        lifetime_points = lifetimePoints;
        team_id = teamId;
    }

    public User(Long ID, String first_name, String last_name, String user_name, String emailAddress) {
        this(0L, first_name, last_name, user_name, emailAddress, new BigDecimal("0"), 0L);
    }

    public User(String first_name, String last_name, String user_name, String emailAddress) {
        this(0L, first_name, last_name, user_name, emailAddress);
    }

    public User create(SQLiteDatabase db, String password) {
        ContentValues values = new ContentValues();
        values.put("user_firstname", this.firstname);
        values.put("user_lastname", this.lastname);
        values.put("user_username", this.username);
        values.put("user_email", this.email);
        values.put("user_password", password);
        values.put("lifetime_points", this.lifetime_points.toString());
        values.put("team_id", this.team_id);

        long newId = db.insert(Tables.UserTable.TABLE_NAME, null, values);
        this.id = newId;
        return this;
    }

    static User loggedInUser = null;
}