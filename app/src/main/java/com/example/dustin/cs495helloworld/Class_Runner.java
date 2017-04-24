package com.example.dustin.cs495helloworld;

import android.provider.ContactsContract;

import java.util.ArrayList;

/**
 * Created by lulu on 2017-04-23.
 */

public class Class_Runner {
    int id;
    String usename;
    String password;
    int presonal_challenges_id;
    int team_challenges_id;
    int team_id;
    String firstname;
    String lastname;
    String email;
    int[] friends;

    public Class_Runner(){
        presonal_challenges_id=-1;
        team_challenges_id=-1;
        team_id=-1;
    }
    public Class_Runner(String u,String pwd){
        usename=u;
        password=pwd;
        presonal_challenges_id=-1;
        team_challenges_id=-1;
        team_id=-1;
    }

    public ArrayList<String> getFriends(){
        ArrayList<String> f=new ArrayList<>();

        return f;
    }

}



