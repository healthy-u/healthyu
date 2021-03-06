package com.example.dustin.cs495helloworld;

import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateChal_sponsor extends AppCompatActivity {

    EditText editstartdate;
    EditText editenddate;
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

    String type="p";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chal_sponsor);

        Button createChalBtn = (Button) findViewById(R.id.createChalBtn);
        RadioButton personalTypeBtn = (RadioButton) findViewById(R.id.rb_personal);
        RadioButton teamTypeByn = (RadioButton) findViewById(R.id.rb_team);

        editstartdate =(EditText)findViewById(R.id.edit_start_date);
        editenddate =(EditText)findViewById(R.id.edit_end_date);

        String currentDateandTime = sdf.format(new Date());
        String tomorrowDateandTime = sdf.format(new Date(new Date().getTime() + (1000 * 60 * 60 * 24)));

        editstartdate.setText(currentDateandTime);
        editenddate.setText(tomorrowDateandTime);

        createChalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnCreated(v);
            }
        });

        personalTypeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rdp(v);
            }
        });

        teamTypeByn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rdt(v);
            }
        });

    }

    void rdp(View v){
        type="p";
    }
    void rdt(View v){
        type="t";
    }

    void  btnCreated (View v){

        String name="",prizelink="",pointsawarded="",startdatestring="",enddatestring="";
        RadioButton personal, team;
        EditText editname = (EditText)findViewById(R.id.edit_name);
        EditText editprizelink = (EditText)findViewById(R.id.edit_prize_link);
        EditText editpointsawarded =(EditText)findViewById(R.id.edit_points_awarded);

        name=editname.getText().toString();
        prizelink=editprizelink.getText().toString();
        pointsawarded=editpointsawarded.getText().toString();
        startdatestring=editstartdate.getText().toString();
        enddatestring=editenddate.getText().toString();

        Date startDate = null;
        Date endDate = null;

        try{
            startDate = sdf.parse(startdatestring);
            endDate = sdf.parse(enddatestring);
        }catch (Exception ex ){
            System.out.println(ex);
        }


        Challenge newChallenge = Tables.ChallengeTable.create(new Challenge(Sponsor.loggedInSponsor.id, new Prize(prizelink, Integer.parseInt(pointsawarded)), startDate, endDate, name, Challenge.CHALLENGE_TYPE_PERSONAL));

        finish();
    }
}
