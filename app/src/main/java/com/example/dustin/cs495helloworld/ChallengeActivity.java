package com.example.dustin.cs495helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ChallengeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    List<String> cname=new ArrayList<String>();
    private ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);
        overridePendingTransition(R.anim.transistion, R.anim.transistion);

        System.out.println("LOGGED IN: " + User.loggedInUser);
        Challenge.challenges = Tables.ChallengeTable.findForUser(User.loggedInUser);

        cname.clear();
        for (int i = 0; i < Challenge.challenges.size(); i++) {
            cname.add(Challenge.challenges.get(i).name);
        }


        listView = (ListView) findViewById(R.id.listview_chal);
        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                cname));

        listView.setOnItemClickListener(this);


        final Button btnStatsPage = (Button) findViewById(R.id.btnStatsPage);
        final Button btnRunPage = (Button) findViewById(R.id.btnRunPage);






        btnRunPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent nextScreen = new Intent(v.getContext(), MapsActivity.class);
                startActivity(nextScreen);
            }
        });

        btnStatsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent nextScreen = new Intent(v.getContext(), StatsActivity.class);
                startActivityForResult(nextScreen, 0);
            }
        });
    }

    public void onItemClick(AdapterView<?> arg0, View vv, int p, long id) {

        Intent n=new Intent(ChallengeActivity.this,join_chal_runner.class);
        n.putExtra("index",p);
        startActivity(n);

    }
}
