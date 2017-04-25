package com.example.dustin.cs495helloworld;

import android.app.AlertDialog;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * Created by lulu on 2017-04-23.
 */

public class Class_Runner_list {
    int size;
    int uid;
    ArrayList<Class_Runner> CRunners;
    ArrayList<Class_Team> CTeams;
    Class_Friend CFriends;


    public Class_Runner_list(){
        size=0;

        CRunners=new ArrayList<>();
        CTeams=new ArrayList<>();
        CFriends=new Class_Friend();

        add(new Class_Runner("p1",""));
        add(new Class_Runner("p2",""));
        add(new Class_Runner("p3",""));
        add(new Class_Runner("p4",""));
        add(new Class_Runner("p5",""));
        add(new Class_Runner("jim",""));
        add(new Class_Runner("hex",""));
        add(new Class_Runner("jack",""));
        add(new Class_Runner("rex",""));
        add(new Class_Runner("connor",""));
        add(new Class_Runner("smith",""));
        add(new Class_Runner("john",""));


        CTeams.add(new Class_Team("Tiger"));
        CTeams.add(new Class_Team("Monkey"));
        CTeams.add(new Class_Team("Bunny"));
        CTeams.add(new Class_Team("Penguin"));
        CTeams.add(new Class_Team("Dragon"));



        CTeams.get(0).join(0);
        CTeams.get(0).join(1);
        CTeams.get(0).join(2);
        CTeams.get(0).join(3);
        CTeams.get(0).join(4);
        CTeams.get(1).join(5);
        CTeams.get(1).join(6);
        CTeams.get(2).join(7);
        CTeams.get(2).join(8);
        CTeams.get(3).join(9);
        CTeams.get(3).join(10);
        CTeams.get(4).join(11);


    }


    public void add(Class_Runner r){

        r.id=size;
        size+=1;
        CRunners.add(r);
    }
    public boolean login(String u,String psw){
        for (int i=0;i<size;i++){
            if(u.equals(CRunners.get(i).usename) && psw.equals(CRunners.get(i).password)){
                return true;
            }
        }

        return false;
    }
    public boolean register(String u,String psw,String fn,String ln,String e){
        for (int i=0;i<size;i++){
            if (u.equals(getname(i))){
                return false;
            }
        }
        Class_Runner r=new Class_Runner(u,psw);
        r.firstname=fn;
        r.lastname=ln;
        r.email=e;
        add(r);

        return true;
    }
    public Class_Runner getRunner(String u){
        Class_Runner cr=new Class_Runner();
        for (int i=0;i<size;i++){
            if (CRunners.get(i).usename.equals(u)){
                cr=CRunners.get(i);
            }
        }
        return cr;
    }


    public String getname(int id){
        String uname="";
        for (int i=0;i<size;i++){
            if (CRunners.get(i).id==id){
                uname=CRunners.get(i).usename;
            }
        }
        return uname;

    }




}
