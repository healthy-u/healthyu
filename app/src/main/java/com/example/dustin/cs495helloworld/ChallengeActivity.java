package com.example.dustin.cs495helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChallengeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);
        overridePendingTransition(R.anim.transistion, R.anim.transistion);

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
}
