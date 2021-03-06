package com.example.dustin.cs495helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import static com.example.dustin.cs495helloworld.MainActivity.CRlst;

public class Register extends AppCompatActivity {


    Boolean isSponsor = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Button btnRegister = (Button) findViewById(R.id.button5);
        final Button btnIsRunner = (Button) findViewById(R.id.rb_runner);
        final Button btnIsSponsor = (Button) findViewById(R.id.rb_sponsor);


        btnIsRunner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSponsor = false;
            }
        });

        btnIsSponsor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSponsor = true;
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    EditText uname = (EditText) findViewById(R.id.euname);
                    EditText pwd = (EditText) findViewById(R.id.epwd);
                    EditText pwd2 = (EditText) findViewById(R.id.epwd2);
                    EditText fn = (EditText) findViewById(R.id.efn);
                    EditText ln = (EditText) findViewById(R.id.eln);
                    EditText em = (EditText) findViewById(R.id.eemail);
                    RadioButton sponsorBtn = (RadioButton) findViewById(R.id.rb_sponsor);


                    String suname = uname.getText().toString();
                    String spwd = pwd.getText().toString();
                    String spwd2 = pwd2.getText().toString();
                    String sfn = fn.getText().toString();
                    String sln = ln.getText().toString();
                    String sem = em.getText().toString();

                    System.out.println("**********************isSponsor? " + isSponsor);

                    if (suname.equals("")) {
                        Toast.makeText(v.getContext(), "Username is empty!", Toast.LENGTH_SHORT).show();
                    } else if (!spwd.equals(spwd2)) {
                        Toast.makeText(v.getContext(), "The password is inconsistent!", Toast.LENGTH_SHORT).show();
                    } else if (!CRlst.register(suname, spwd, sfn, sln, sem)) {
                        Toast.makeText(v.getContext(), suname + ": exist!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (isSponsor) {
                            Sponsor newSponsor = Tables.SponsorTable.create(new Sponsor(suname, sem), spwd);
                            Sponsor.loggedInSponsor = newSponsor;
                            Intent nextScreen = new Intent(v.getContext(), Main_sponsor.class);
                            startActivityForResult(nextScreen, 0);
                        } else {
                            User newUser = Tables.UserTable.create(new User(sfn, sln, suname, sem), spwd);
                            User.loggedInUser = newUser;
                            Intent nextScreen = new Intent(v.getContext(), MapsActivity.class);
                            startActivityForResult(nextScreen, 0);
                        }
                        Toast.makeText(v.getContext(), suname + ": accountCreated", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
            }
        });
    }
}
