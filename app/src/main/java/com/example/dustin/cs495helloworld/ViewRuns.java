package com.example.dustin.cs495helloworld;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewRuns extends AppCompatActivity {
    Button b1,/*b2,*/b3,b4;
    Fragment f1 ,f2,f3,f4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_runs);

        overridePendingTransition(R.anim.transistion, R.anim.transistion);

        final Button btnRunPage = (Button) findViewById(R.id.btnRunPage);
        final Button btnChallengePage = (Button) findViewById(R.id.btnChallengePage);

        btnRunPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent nextScreen = new Intent(v.getContext(), MapsActivity.class);
                startActivityForResult(nextScreen, 0);
            }
        });

        btnChallengePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent nextScreen = new Intent(v.getContext(), ChallengeActivity.class);
                startActivityForResult(nextScreen, 0);
            }
        });

        init();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonOnClick1(v);
            }
        });

        /*b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonOnClick2(v);
            }
        });*/

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonOnClick3(v);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonOnClick4(v);
            }
        });

    }


    protected void init(){
        b1=(Button)findViewById(R.id.button1);
        //b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button4);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        f1 = new Fpersonal();
        transaction.replace(R.id.fragment1, f1);

        transaction.commit();

    }

    protected void buttonOnClick1(View view) {
        b1.setTextColor(getResources().getColor(R.color.black));
        //b2.setTextColor(getResources().getColor(R.color.white));
        b3.setTextColor(getResources().getColor(R.color.white));
        b4.setTextColor(getResources().getColor(R.color.white));
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        f1 = new Fpersonal();
        transaction.replace(R.id.fragment1, f1);

        transaction.commit();


    }

    /*protected void buttonOnClick2(View view) {
        b1.setTextColor(getResources().getColor(R.color.white));
        b2.setTextColor(getResources().getColor(R.color.black));
        b3.setTextColor(getResources().getColor(R.color.white));
        b4.setTextColor(getResources().getColor(R.color.white));


        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if(f2== null){
            f2 = new frends();
        }
        transaction.replace(R.id.fragment1, f2);

        transaction.commit();

    }*/

    protected void buttonOnClick3(View view) {
        b1.setTextColor(getResources().getColor(R.color.white));
        //b2.setTextColor(getResources().getColor(R.color.white));
        b3.setTextColor(getResources().getColor(R.color.black));
        b4.setTextColor(getResources().getColor(R.color.white));

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if(f3== null){
            f3 = new team_adapter();
        }
        transaction.replace(R.id.fragment1, f3);

        transaction.commit();


    }
    protected void buttonOnClick4(View view) {
        b1.setTextColor(getResources().getColor(R.color.white));
        //b2.setTextColor(getResources().getColor(R.color.white));
        b3.setTextColor(getResources().getColor(R.color.white));
        b4.setTextColor(getResources().getColor(R.color.black));
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if(f4== null){
            f4 = new campus();
        }
        transaction.replace(R.id.fragment1, f4);

        transaction.commit();
    }
}
