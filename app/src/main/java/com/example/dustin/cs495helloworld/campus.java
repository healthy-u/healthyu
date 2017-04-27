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
 *
 *
 */

public class campus extends Fragment implements AdapterView.OnItemClickListener {
    List<String> tname =new ArrayList<String>();
    private List<Team> teams = new ArrayList<>();
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

        teams = Tables.TeamTable.findAllTeamsForUserCampus(User.loggedInUser.id);

        tname.clear();
        for(int i=0;i<teams.size(); i++){
            tname.add(teams.get(i).name + "- Total Points: " + teams.get(i).totalPoints());
        }

        myAdapter= new ArrayAdapter<String>(getActivity(),
                R.layout.simple_list_item_1,
                tname);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(this);


    }
    public void onItemClick(AdapterView<?> arg0, View vv, int p, long id) {

        //String uname= tname.get(p);
        //int i=CRlst.getRunner(uname).id;
        //Intent n=new Intent(getActivity(),runner_info.class);
        //n.putExtra("id",i);
        //startActivity(n);

    }
}
