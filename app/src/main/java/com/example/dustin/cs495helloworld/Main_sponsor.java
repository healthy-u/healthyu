package com.example.dustin.cs495helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.List;


public class Main_sponsor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sponsor);

        Button createChallengeBtn=(Button)findViewById(R.id.sbtnCreateChal);
        Button viewChallengeBtn =(Button)findViewById(R.id.sbtnViewChal);
        Button editChallengeBtn=(Button)findViewById(R.id.sbtnEditChal);
        Button delChallengeButton=(Button)findViewById(R.id.sbtnDelChal);
        ImageButton settingsBtn=(ImageButton)findViewById(R.id.sbtnSettings);

        createChallengeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sbtnCreate(v);
            }
        });

        viewChallengeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sbtnView(v);
            }
        });

        editChallengeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sbtnEdit(v);
            }
        });

        delChallengeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sbtnDel(v);
            }
        });

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sbtnSettings(v);
            }
        });


    }



    void  sbtnCreate (View v){
        Intent n = new Intent(Main_sponsor.this, CreateChal_sponsor.class);
        startActivity(n);
    }

    void sbtnView(View v){
        Intent n = new Intent(Main_sponsor.this, ChalList_s.class);
        startActivity(n);
    }
    void sbtnEdit(View v){
        Intent n = new Intent(Main_sponsor.this, EditChal_sponsor.class);
        startActivity(n);
    }
    void sbtnDel(View v){
        Intent n = new Intent(Main_sponsor.this, DelChal_sponsor.class);
        startActivity(n);
    }
    void sbtnSettings(View v){
        Intent n = new Intent(Main_sponsor.this, Set_sponsor.class);
        startActivity(n);
    }


}
