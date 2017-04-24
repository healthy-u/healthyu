package com.example.dustin.cs495helloworld;

import java.util.ArrayList;

/**
 * Created by lulu on 2017-04-22.
 */

 public  class Class_Challenges_list {
    int size;
    int idbase;
    ArrayList<Class_Challenge> Clist=new ArrayList<Class_Challenge>();



    public Class_Challenges_list(){
        size=0;
        idbase=0;
        Class_Challenge c1=new Class_Challenge("challenge example1","this is example1",1000,"2017","p");
        Class_Challenge c2=new Class_Challenge("challenge example2","this is example2",2000,"2017","t");
        Class_Challenge c3=new Class_Challenge("challenge example3","this is example3",500,"2017","t");

        this.add(c1);
        this.add(c2);
        this.add(c3);

    }


    public void add(Class_Challenge cc){

        cc.id=idbase;
        size+=1;
        idbase+=1;
        this.Clist.add(cc);
    }
    public void remove(int n){
        this.size-=1;
        this.Clist.remove(n);
    }


}
