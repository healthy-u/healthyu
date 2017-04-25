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
    Long prize_id;
    Date start_date;
    Date end_date;
    String name;


    public Challenge(Long ID, Long Sponsor_ID, Long Prize_ID, Date Start_Date, Date End_Date, String Name) {
        this.id = ID;
        this.sponsor_id = Sponsor_ID;
        this.prize_id = Prize_ID;
        this.start_date = Start_Date;
        this.end_date = End_Date;
        this.name = Name;
    }

    public static List<Challenge> challenges = new ArrayList<Challenge>();
}
