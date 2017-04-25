package com.example.dustin.cs495helloworld;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.dustin.cs495helloworld.MainActivity.CRlst;

/**
 * Created by lulu on 2017-04-06.
 */

public class team_adapter extends Fragment implements AdapterView.OnItemClickListener {


    List<String> tname=new ArrayList<String>();
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
        tname.clear();
        for (int i=0;i<CRlst.CTeams.size();i++){
            tname.add(CRlst.CTeams.get(i).name);
        }


        myAdapter= new ArrayAdapter<String>(getActivity(),
                R.layout.simple_list_item_1,
                tname);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(this);

    }
    public void onItemClick(AdapterView<?> arg0, View vv, int p, long id) {



        if(CRlst.CTeams.get(p).join(CRlst.uid)){
            CRlst.CRunners.get(CRlst.uid).team_id=p;
            Toast.makeText(getActivity(), "joined", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(), "join Failed", Toast.LENGTH_SHORT).show();
        }

    }
}
