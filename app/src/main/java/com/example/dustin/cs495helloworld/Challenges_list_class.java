package com.example.dustin.cs495helloworld;

import java.util.ArrayList;

/**
 * Created by lulu on 2017-04-22.
 */

 public  class Challenges_list_class {
    int size=0;
    ArrayList<Challenge_class> Clist=new ArrayList<Challenge_class>();

    public  Challenges_list_class(){}


    public void add(Challenge_class cc){
        this.size+=1;
        this.Clist.add(cc);
    }
    public void remove(int n){
        this.size-=1;
        this.Clist.remove(n);
    }


}
