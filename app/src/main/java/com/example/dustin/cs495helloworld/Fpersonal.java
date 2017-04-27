package com.example.dustin.cs495helloworld;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static com.example.dustin.cs495helloworld.MainActivity.CRlst;


public class Fpersonal  extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.personal, container, false);
        TextView uanme =(TextView) v.findViewById(R.id.textView3);
        TextView tRank =(TextView) v.findViewById(R.id.textView14);
        TextView cRank =(TextView) v.findViewById(R.id.textView15);
        TextView LR =(TextView) v.findViewById(R.id.textView16);
        TextView Most =(TextView) v.findViewById(R.id.textView17);

        TextView l1 =(TextView) v.findViewById(R.id.textView19);
        TextView l2 =(TextView) v.findViewById(R.id.textView20);
        TextView l3 =(TextView) v.findViewById(R.id.textView21);
        TextView l4 =(TextView) v.findViewById(R.id.textView22);
        TextView l5 =(TextView) v.findViewById(R.id.textView23);

        String[] runs = {"------------------------------------", "------------------------------------", "------------------------------------",
                "------------------------------------", "------------------------------------"};

        for (int i = 0; i < Math.min(User.loggedInUser.runs.size(), 5); i++) {
            Run r = User.loggedInUser.runs.get(i);
            try{
                runs[i] = Tables.dateFormat.format(r.startTime) + "- " + r.miles + " miles";
            }
            catch (Exception e) {
                //do nothing
            }
        }

        uanme.setText(CRlst.getname(CRlst.uid));
        tRank.setText("null");
        cRank.setText("null");
        LR.setText(User.loggedInUser.longestRun().miles.toString());
        Most.setText(User.loggedInUser.mostStepsPerDay().toString());
        l1.setText(runs[0]);
        l2.setText(runs[1]);
        l3.setText(runs[2]);
        l4.setText(runs[3]);
        l5.setText(runs[4]);




        return v;
    }
}
