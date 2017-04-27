package com.example.dustin.cs495helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;


public class challenge_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_info);

        Button backBtn = (Button) findViewById(R.id.button5);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnBack(v);
            }
        });

        Intent n=getIntent();
        EditText Tname,Tinfo,Ttag,Tdate;
        RadioButton rbp,rbt;
        int i=n.getIntExtra("index",0);
        Challenge chal=Challenge.challenges.get(i);

        Tname=(EditText) findViewById(R.id.edit_name);
        Tname.setText(chal.name);
        Tinfo=(EditText) findViewById(R.id.edit_points_awarded);
        Tinfo.setText(chal.id.toString());
        Ttag=(EditText) findViewById(R.id.edit_target);
        //Ttag.setText(String.valueOf(chal.prize.id));
        Tdate=(EditText) findViewById(R.id.edit_end_date);
        Tdate.setText(chal.start_date + "-" + chal.end_date);
        rbp=(RadioButton)findViewById(R.id.rb_personal);
        rbt=(RadioButton)findViewById(R.id.rb_team);
        /*if(chal.type.equals("t")){
            rbt.setChecked(true);
            rbp.setChecked(false);

        }else{*/
            rbp.setChecked(true);
            rbt.setChecked(false);
        //}
        rbt.setEnabled(false);
        rbp.setEnabled(false);
    }
    void btnBack(View v){
        finish();
    }
}
