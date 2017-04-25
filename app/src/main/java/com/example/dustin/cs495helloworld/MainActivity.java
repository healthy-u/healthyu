package com.example.dustin.cs495helloworld;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Date;

import static com.example.dustin.cs495helloworld.R.id.btnRegister;
public class MainActivity extends AppCompatActivity {


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    public static final Class_Runner_list  CRlst=new Class_Runner_list();

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final Button btnRegister = (Button) findViewById(R.id.btnRegister);
        final EditText usernameTxt = (EditText) findViewById(R.id.usernameTxt);
        final EditText passwordTxt = (EditText) findViewById(R.id.passwordTxt);

        final String[] INITIAL_PERMS = {
                Manifest.permission.ACCESS_FINE_LOCATION
        };
        final int INITIAL_REQUEST = 1;

        ActivityCompat.requestPermissions(MainActivity.this, INITIAL_PERMS, INITIAL_REQUEST);

        Long currentUserId = Long.parseLong(SaveSharedPreference.getUserName(MainActivity.this));

        if (currentUserId != null && currentUserId != 0L)
        {
            SQLiteDatabase db = new Database(this).getReadableDatabase();
            User.loggedInUser = Tables.UserTable.findForID(currentUserId);
            System.out.println("******************");
            System.out.println("LOGGED IN : " + User.loggedInUser);
            //  use
            Intent nextScreen = new Intent(this, MapsActivity.class);
            startActivityForResult(nextScreen, 0);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = new Database(v.getContext()).getWritableDatabase();

                String username = usernameTxt.getText().toString();
                String password = passwordTxt.getText().toString();


                // sponsor login

                if (username.equals("sponsor")) {
                    Intent nextScreen = new Intent(v.getContext(), Main_sponsor.class);
                    startActivityForResult(nextScreen, 0);

                }else if (CRlst.login(username,password)){
                    Toast.makeText(v.getContext(), usernameTxt.getText().toString() + ": login successful", Toast.LENGTH_SHORT).show();
                    Intent nextScreen = new Intent(v.getContext(), MapsActivity.class);
                    int id=CRlst.getRunner(username).id;
                    CRlst.uid=id;
                    nextScreen.putExtra("id",id);
                    startActivityForResult(nextScreen, 0);

                }else{
                    Toast.makeText(v.getContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                }/*
                else {

                    User u = Tables.UserTable.findForUsernameAndPassword(username, password);

                    if (u != null) {
                        User.loggedInUser = u;
                        SaveSharedPreference.setUserName(v.getContext(), u.id);
                        //Run run = new Run(User.loggedInUser.id, new Date()).create(db);
                        System.out.println("User.loggedInUser.id: " + User.loggedInUser.id);
                        Toast.makeText(v.getContext(), usernameTxt.getText().toString() + ": login successful", Toast.LENGTH_SHORT).show();

                        Intent nextScreen = new Intent(v.getContext(), MapsActivity.class);
                        startActivityForResult(nextScreen, 0);

                    } else {
                        Sponsor s = Tables.SponsorTable.findForUsernameAndPassword(username, password);
                        if (s != null) {
                            Sponsor.loggedInSponsor = s;
                            //SaveSharedPreference.setSponsorName(v.getContext(), u.id);
                            // Run run = new Run(User.loggedInUser.id, new Date()).create(db);
                            System.out.println("Sponsor.loggedInSponsor.id: " + Sponsor.loggedInSponsor.id);
                            Toast.makeText(v.getContext(), usernameTxt.getText().toString() + ": login successful", Toast.LENGTH_SHORT).show();

                            Intent nextScreen = new Intent(v.getContext(), MapsActivity.class);
                            startActivityForResult(nextScreen, 0);
                            }

                        Toast.makeText(v.getContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }*/

                 passwordTxt.setText("");
            }


        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                EditText uname=(EditText) findViewById(R.id.usernameTxt);
                EditText psd=(EditText) findViewById(R.id.passwordTxt);

                String username = uname.getText().toString();
                String password = psd.getText().toString();

                if (! CRlst.register(username,password)){
                    Toast.makeText(v.getContext(), username + ": exist!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(v.getContext(),  username+ ": accountCreated", Toast.LENGTH_SHORT).show();

                }*/
                Intent nextScreen = new Intent(v.getContext(), regisetr.class);
                startActivityForResult(nextScreen, 0);

                /*


                User user = new User("First", "Name", username, "email@email.com");

                SQLiteDatabase db = new Database(v.getContext()).getWritableDatabase();

                User.loggedInUser = user.create(db, password);

                Toast.makeText(v.getContext(), User.loggedInUser.username + ": accountCreated", Toast.LENGTH_SHORT).show();
                Intent nextScreen = new Intent(v.getContext(), MapsActivity.class);
                startActivityForResult(nextScreen, 0);

                passwordTxt.setText("");*/
            }


        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}