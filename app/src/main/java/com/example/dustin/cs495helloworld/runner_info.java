package com.example.dustin.cs495helloworld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.dustin.cs495helloworld.MainActivity.CRlst;

public class runner_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent n=getIntent();
        int i=n.getIntExtra("id",0);
        setContentView(R.layout.activity_runner_info);
        User u = Tables.UserTable.findForID(new Long(i));

        Button backBtn = (Button) findViewById(R.id.button5);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView uanme =(TextView) findViewById(R.id.textView3);
        TextView tRank =(TextView) findViewById(R.id.textView14);
        TextView cRank =(TextView) findViewById(R.id.textView15);
        TextView LR =(TextView) findViewById(R.id.textView16);
        TextView Most =(TextView) findViewById(R.id.textView17);

        TextView l1 =(TextView) findViewById(R.id.textView19);
        TextView l2 =(TextView) findViewById(R.id.textView20);
        TextView l3 =(TextView) findViewById(R.id.textView21);
        TextView l4 =(TextView) findViewById(R.id.textView22);
        TextView l5 =(TextView) findViewById(R.id.textView23);

        String[] runs = {"------------------------------------", "------------------------------------", "------------------------------------",
                "------------------------------------", "------------------------------------"};

        for (int j = 0; j < Math.min(User.loggedInUser.runs.size(), 5); j++) {
            Run r = u.runs.get(j);
            try{
                runs[j] = Tables.dateFormat.format(r.startTime) + "- " + r.miles + " miles";
            }
            catch (Exception e) {
                //do nothing
            }
        }



        uanme.setText(u.fullname());
        tRank.setText("3");
        cRank.setText("5");
        LR.setText(u.longestRun().miles.toString());
        Most.setText(u.mostStepsPerDay().toString());
        l1.setText(runs[0]);
        l2.setText(runs[1]);
        l3.setText(runs[2]);
        l4.setText(runs[3]);
        l5.setText(runs[4]);



    }

    void btnBack(View v){
        finish();
    }
}
