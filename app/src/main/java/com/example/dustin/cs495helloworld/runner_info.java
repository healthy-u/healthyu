package com.example.dustin.cs495helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.example.dustin.cs495helloworld.MainActivity.CRlst;

public class runner_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent n=getIntent();
        int i=n.getIntExtra("id",0);
        setContentView(R.layout.activity_runner_info);
        String  rr=CRlst.getname(i);

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



        uanme.setText(rr);
        tRank.setText("null");
        cRank.setText("null");
        LR.setText("null");
        Most.setText("null");
        l1.setText("Null");
        l2.setText("Null");
        l3.setText("Null");
        l4.setText("Null");
        l5.setText("Null");



    }

    void btnBack(View v){
        finish();
    }
}
