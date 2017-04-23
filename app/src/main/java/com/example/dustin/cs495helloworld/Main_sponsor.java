package com.example.dustin.cs495helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;


public class Main_sponsor extends AppCompatActivity {



    public static final Challenges_list_class Chal_list=new Challenges_list_class();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sponsor);
        Challenge_class c1=new Challenge_class("challenge example1","this is example1",1000,"2017","p");
        Challenge_class c2=new Challenge_class("challenge example2","this is example2",2000,"2017","t");
        Challenge_class c3=new Challenge_class("challenge example3","this is example3",500,"2017","t");
        Chal_list.add(c1);
        Chal_list.add(c2);
        Chal_list.add(c3);

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
