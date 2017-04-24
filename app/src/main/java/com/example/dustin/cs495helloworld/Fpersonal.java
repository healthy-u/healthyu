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



        uanme.setText(CRlst.getname(CRlst.uid));
        tRank.setText("null");
        cRank.setText("null");
        LR.setText("null");
        Most.setText("null");
        l1.setText("Null");
        l2.setText("Null");
        l3.setText("Null");
        l4.setText("Null");
        l5.setText("Null");




        return v;
    }
}
