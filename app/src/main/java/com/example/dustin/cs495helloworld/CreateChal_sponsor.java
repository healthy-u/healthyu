package com.example.dustin.cs495helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import static com.example.dustin.cs495helloworld.Main_sponsor.Chal_list;

public class CreateChal_sponsor extends AppCompatActivity {

    String type="p";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chal_sponsor);

    }

    void rdp(View v){
        type="p";
    }
    void rdt(View v){
        type="t";
    }

    void  btnCreated (View v){

        String name="",target="",info="",date="";
        RadioButton personal;
        EditText editname =(EditText)findViewById(R.id.edit_name);
        EditText editinfo =(EditText)findViewById(R.id.edit_info);
        EditText edittarget=(EditText)findViewById(R.id.edit_target);
        EditText editdate =(EditText)findViewById(R.id.edit_date);

        name=editname.getText().toString();
        info=editinfo.getText().toString();
        target=edittarget.getText().toString();
        date=editdate.getText().toString();

        Class_Challenge Chal=new Class_Challenge(name,info,Integer.parseInt(target),date,type);
        Chal_list.add(Chal);

        finish();
    }
}
