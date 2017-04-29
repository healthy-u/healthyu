package com.example.dustin.cs495helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class challenge_info_edit extends AppCompatActivity {
    String type="p";
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_info_edit);
        Intent n=getIntent();
        EditText Tname,Tinfo,Ttag,Tdate;
        RadioButton rbp,rbt;
        i=n.getIntExtra("index",0);
        Challenge chal=Challenge.challenges.get(i);

        Tname=(EditText) findViewById(R.id.edit_name);
        Tname.setText(chal.name);
        Tinfo=(EditText) findViewById(R.id.edit_points_awarded);
        Tinfo.setText(chal.id.toString());
        Ttag=(EditText) findViewById(R.id.edit_target);
        //Ttag.setText(String.valueOf(chal.prize.id.toString()));
        Tdate=(EditText) findViewById(R.id.edit_end_date);
        Tdate.setText(chal.start_date + "-" + chal.end_date);
        rbp=(RadioButton)findViewById(R.id.rb_personal);
        rbt=(RadioButton)findViewById(R.id.rb_team);
        if(chal.type.equals("t")){
            rbt.setChecked(true);
            rbp.setChecked(false);
            type="t";

        }else{
            rbp.setChecked(true);
            rbt.setChecked(false);
            type="p";
        }

    }





    void btnsave(View v){
        String name="",target="",info="",date="";
        EditText editname =(EditText)findViewById(R.id.edit_name);
        EditText editinfo =(EditText)findViewById(R.id.edit_points_awarded);
        EditText edittarget=(EditText)findViewById(R.id.edit_target);
        EditText editdate =(EditText)findViewById(R.id.edit_end_date);
        name=editname.getText().toString();
        info=editinfo.getText().toString();
        target=edittarget.getText().toString();
        date=editdate.getText().toString();
        Challenge chal=Challenge.challenges.get(i);
        chal.name=name;
        //chal.info=info;
        //chal.target=Integer.parseInt(target);
        //chal.due_Date=date;
        //chal.type=type;
        finish();


    }
    void rdp(View v){
        type="p";
    }
    void rdt(View v){
        type="t";
    }

}
