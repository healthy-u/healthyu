package com.example.dustin.cs495helloworld;

/**
 * Created by lulu on 2017-04-23.
 */

public class Class_Friend {
    int[][] friendsTable;

    public Class_Friend(){}

    public boolean isFriend(int p1,int p2){
        for (int i=0;i<friendsTable.length;i++){
            if (friendsTable[p1][p2]==1){
                return true;
            }
        }
        return false;

    }

    public void makefriend(int p1,int p2){
        friendsTable[p1][p2]=1;
        friendsTable[p2][p1]=1;
    }

    public void delfriend(int p1,int p2){
        friendsTable[p1][p2]=0;
        friendsTable[p2][p1]=0;
    }
}
