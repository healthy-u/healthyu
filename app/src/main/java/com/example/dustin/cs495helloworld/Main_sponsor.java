package com.example.dustin.cs495helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class Main_sponsor extends AppCompatActivity {



    public static final Class_Challenges_list Chal_list=new Class_Challenges_list();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sponsor);

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
    void sbtnSet(View v){
        Intent n = new Intent(Main_sponsor.this, Set_sponsor.class);
        startActivity(n);
    }


}
