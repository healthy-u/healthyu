package com.example.dustin.cs495helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.dustin.cs495helloworld.Main_sponsor.Chal_list;


public class challenge_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_info);
        Intent n=getIntent();
        EditText Tname,Tinfo,Ttag,Tdate;
        RadioButton rbp,rbt;
        int i=n.getIntExtra("index",0);
        Challenge_class chal=Chal_list.Clist.get(i);

        Tname=(EditText) findViewById(R.id.edit_name);
        Tname.setText(chal.name);
        Tinfo=(EditText) findViewById(R.id.edit_info);
        Tinfo.setText(chal.info);
        Ttag=(EditText) findViewById(R.id.edit_target);
        Ttag.setText(String.valueOf(chal.target));
        Tdate=(EditText) findViewById(R.id.edit_date);
        Tdate.setText(chal.due_Date);
        rbp=(RadioButton)findViewById(R.id.rb_personal);
        rbt=(RadioButton)findViewById(R.id.rb_team);
        if(chal.type.equals("t")){
            rbt.setChecked(true);
            rbp.setChecked(false);

        }else{
            rbp.setChecked(true);
            rbt.setChecked(false);
        }
        rbt.setEnabled(false);
        rbp.setEnabled(false);
    }
    void btnBack(View v){
        finish();
    }
}
