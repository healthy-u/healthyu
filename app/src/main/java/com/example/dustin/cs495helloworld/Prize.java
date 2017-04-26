package com.example.dustin.cs495helloworld;

import java.math.BigDecimal;

/**
 * Created by dustin on 4/25/17.
 */

public class Prize {
    Long id = 0L;
    Long winner_id = 0L;
    String prize_link;
    int points_awarded = 0;
    Boolean redeemed = false;

    public Prize(Long ID, Long Winner_ID, String Prize_Link, int Points_Awarded, Boolean Redeemed) {
        id = ID;
        winner_id = Winner_ID;
        prize_link = Prize_Link;
        points_awarded = Points_Awarded;
        redeemed = Redeemed;
    }

    public Prize(String Prize_Link, int Points_Awarded) {
        prize_link = Prize_Link;
        points_awarded = Points_Awarded;
    }
}
