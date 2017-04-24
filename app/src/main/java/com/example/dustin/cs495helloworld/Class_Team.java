package com.example.dustin.cs495helloworld;

import android.content.Intent;

import java.util.ArrayList;

/**
 * Created by lulu on 2017-04-23.
 */

public class Class_Team {
    int id;
    int size;
    String name;
    ArrayList<Integer> Trunners;

    public Class_Team(){
        Trunners=new ArrayList<>();
    }
    public Class_Team(String tname){
        name=tname;
        Trunners=new ArrayList<>();
    }

    public boolean join(int i){
        if (size<5) {
            Trunners.add(i);
            size += 1;
            return true;
        }else{
            return false;
        }
    }


}
