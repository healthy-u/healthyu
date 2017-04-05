package com.example.dustin.cs495helloworld;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by dustin on 4/4/17.
 */

public class SettingsActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final Button btnLogout = (Button) findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = SaveSharedPreference.getSharedPreferences(v.getContext()).edit();
                editor.clear(); //clear all stored data
                editor.commit();

                Intent nextScreen = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(nextScreen, 0);
            };
        });
    }
}
