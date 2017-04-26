package com.example.dustin.cs495helloworld;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lulu on 2017-04-22.
 */

public class Challenge {
    Long id;
    Long sponsor_id;
    Prize prize;
    Date start_date;
    Date end_date;
    String name;
    String type;

    public static String CHALLENGE_TYPE_PERSONAL = "Personal";
    public static String CHALLENGE_TYPE_TEAM = "Team";


    public Challenge(Long ID, Long Sponsor_ID, Prize prizeParam, Date Start_Date, Date End_Date, String Name, String Type) {
        this.id = ID;
        this.sponsor_id = Sponsor_ID;
        this.prize = prizeParam;
        this.start_date = Start_Date;
        this.end_date = End_Date;
        this.name = Name;
        this.type = Type;
    }

    public Challenge(Long Sponsor_ID, Prize prizeParam, Date Start_Date, Date End_Date, String Name, String Type) {
        this(0L, Sponsor_ID, prizeParam, Start_Date, End_Date, Name, Type);
    }

    public static List<Challenge> challenges = new ArrayList<Challenge>();
}
