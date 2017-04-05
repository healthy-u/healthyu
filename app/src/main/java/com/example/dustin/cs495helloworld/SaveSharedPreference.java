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

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, Long userId)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(Long.toString(LOGGED_IN_USER_ID), Long.toString(userId));
        editor.commit();
    }

    public static String getUserName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(Long.toString(LOGGED_IN_USER_ID), Long.toString(0L));
    }
}