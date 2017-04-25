package com.example.dustin.cs495helloworld;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

import static com.example.dustin.cs495helloworld.MainActivity.CRlst;

public class frends  extends Fragment implements AdapterView.OnItemClickListener {




    List<String> cname=new ArrayList<String>();
    private ListView listView ;
    private ArrayAdapter myAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frends, container, false);
        listView = (ListView) rootView.findViewById(R.id.listView_friend);
        initAdapter();

        return rootView;
    }

    private void initAdapter(){
        cname.clear();
        if (CRlst.CRunners.get(CRlst.uid).team_id>0) {
            Class_Team tt=CRlst.CTeams.get(CRlst.CRunners.get(CRlst.uid).team_id);
            for (int i = 0; i < tt.size; i++) {
                int ttid = tt.Trunners.get(i);
                cname.add(CRlst.getname(ttid));
            }
        }
        myAdapter= new ArrayAdapter<String>(getActivity(),
                R.layout.simple_list_item_1,
                cname);


        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(this);


    }
    public void onItemClick(AdapterView<?> arg0, View vv, int p, long id) {

        String uname=cname.get(p);
        int i=CRlst.getRunner(uname).id;
        Intent n=new Intent(getActivity(),runner_info.class);
        n.putExtra("id",i);
        startActivity(n);

    }



}
