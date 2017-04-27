package com.example.dustin.cs495helloworld;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by dustin on 3/30/17.
 */

public class Team {
    Long id = 0L;
    String name;
    List<User> members;

    public Team(Long ID, String team_name, List<User> team_members) {
        id = ID;
        name = team_name;
        members = team_members;
    }

    public BigDecimal totalPoints() {
        BigDecimal total = new BigDecimal("0.0");
        for (int i = 0; i < members.size(); i++) {
            total = total.add(members.get(i).lifetime_points);
        }

        return total;
    }
}