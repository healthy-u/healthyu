package com.example.dustin.cs495helloworld;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.math.BigDecimal;

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
    long team_id = 0L;

    public String fullname()
    {
        return firstname + " " + lastname;
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