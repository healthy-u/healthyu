package com.example.dustin.cs495helloworld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Set_sponsor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_sponsor);
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


    void btnsave(View v){

        finish();
    }
}
