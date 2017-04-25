package com.example.dustin.cs495helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.dustin.cs495helloworld.MainActivity.CRlst;

public class regisetr extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regisetr);
    }
    void btnRegister(View v){
        EditText uname=(EditText) findViewById(R.id.euname);
        EditText pwd=(EditText) findViewById(R.id.epwd);
        EditText pwd2=(EditText) findViewById(R.id.epwd2);
        EditText fn=(EditText) findViewById(R.id.efn);
        EditText ln=(EditText) findViewById(R.id.eln);
        EditText em=(EditText) findViewById(R.id.eemail);


        String suname = uname.getText().toString();
        String spwd = pwd.getText().toString();
        String spwd2= pwd2.getText().toString();
        String sfn = fn.getText().toString();
        String sln = ln.getText().toString();
        String sem = em.getText().toString();
        if (suname.equals("") ) {
            Toast.makeText(v.getContext(), "Username is empty!", Toast.LENGTH_SHORT).show();
        }else if(!spwd.equals(spwd2)){
            Toast.makeText(v.getContext(), "The password is inconsistent!", Toast.LENGTH_SHORT).show();
        }else if (! CRlst.register(suname,spwd,sfn,sln,sem)){
            Toast.makeText(v.getContext(), suname + ": exist!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(v.getContext(),  suname+ ": accountCreated", Toast.LENGTH_SHORT).show();
            finish();
        }

    }
}
