package com.example.dustin.cs495helloworld;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ChalList_s extends AppCompatActivity implements AdapterView.OnItemClickListener {

    List<String> cname=new ArrayList<String>();
    private ListView listView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chal_list_s);
        Challenge.challenges = Tables.ChallengeTable.findForUser(User.loggedInUser);
        cname.clear();
        for (int i=0;i<Challenge.challenges.size();i++){
            cname.add(Challenge.challenges.get(i).name);
        }


        listView =(ListView) findViewById(R.id.listview_chal);
        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                cname));

        listView.setOnItemClickListener(this);


    }


    public void onItemClick(AdapterView<?> arg0, View vv, int p, long id) {

        Intent n=new Intent(ChalList_s.this,challenge_info.class);
        n.putExtra("index",p);
        startActivity(n);

    }


    void btnback(View v){
        finish();
    }



}
