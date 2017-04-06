package com.example.dustin.cs495helloworld;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StatsActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        overridePendingTransition(R.anim.transistion, R.anim.transistion);

        final Button btnRunPage = (Button) findViewById(R.id.btnRunPage);
        final Button btnChallengePage = (Button) findViewById(R.id.btnChallengePage);

        btnRunPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent nextScreen = new Intent(v.getContext(), MapsActivity.class);
                startActivity(nextScreen);
            }
        });

        btnChallengePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent nextScreen = new Intent(v.getContext(), ChallengeActivity.class);
                startActivityForResult(nextScreen, 0);
            }
        });
    }
}
