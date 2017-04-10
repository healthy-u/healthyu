package com.example.dustin.cs495helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main_sponsor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sponsor);
    }



    void  sbtnCreate (View v){
            Intent n = new Intent(v.getContext(), CreateChal_sponsor.class);
            startActivity(n);
    }

    void sbtnView(View v){
        Intent n = new Intent(v.getContext(), ChalList_s.class);
        startActivity(n);
    }
    void sbtnEdit(View v){
        Intent n = new Intent(v.getContext(), ChalList_s.class);
        startActivity(n);
    }
    void sbtnDel(View v){
        Intent n = new Intent(v.getContext(), ChalList_s.class);
        startActivity(n);
    }

}
