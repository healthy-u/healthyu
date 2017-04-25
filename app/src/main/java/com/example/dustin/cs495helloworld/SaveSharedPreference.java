package com.example.dustin.cs495helloworld;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by dustin on 4/4/17.
 */

public class SaveSharedPreference
{
    static final long LOGGED_IN_USER_ID = 0L;
    static final long LOGGED_IN_SPONSOR_ID = 0L;

    static final String userIdString = "USER_ID";
    static final String sponsorIdString = "SPONSOR_ID";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, Long userId)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(userIdString, Long.toString(userId));
        editor.commit();
    }

    public static String getUserName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(userIdString, Long.toString(0L));
    }

    public static void setSponsorID(Context ctx, Long sponsorId)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(sponsorIdString, Long.toString(sponsorId));
        editor.commit();
    }

    public static String getSponsorID(Context ctx)
    {
        return getSharedPreferences(ctx).getString(sponsorIdString, Long.toString(0L));
    }
}