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
    List<String> membernames=new ArrayList<String>();
    private ListView listView ;
    private ArrayAdapter myAdapter;
    private List<Team> teams = new ArrayList<>();
    private List<User> teammates = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frends, container, false);
        listView = (ListView) rootView.findViewById(R.id.listView_friend);
        initAdapter();

        return rootView;
    }
    private void initAdapter(){

        if (User.loggedInUser.team_id == 0L) {
            teams = Tables.TeamTable.allTeamsForUserCampus(User.loggedInUser.id);
        }
        else teams.add(Tables.TeamTable.findForUser(User.loggedInUser.id));

        tname.clear();
        for (int i=0;i<teams.size();i++){
            tname.add(teams.get(i).name);
        }

        membernames.clear();
        for (int i = 0; i < teams.get(0).members.size(); i++) {
            User member = teams.get(0).members.get(i);
            membernames.add(member.fullname() +": " + member.lifetime_points);
        }

        if (User.loggedInUser.team_id == 0L) {
            myAdapter= new ArrayAdapter<String>(getActivity(), R.layout.simple_list_item_1, tname);
        }
        else {
            myAdapter= new ArrayAdapter<String>(getActivity(), R.layout.simple_list_item_1, membernames);
        }


        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(this);

    }
    public void onItemClick(AdapterView<?> arg0, View vv, int p, long id) {

        if (User.loggedInUser.team_id != 0L) {
            Toast.makeText(getActivity(), "You're already on a team!", Toast.LENGTH_SHORT).show();
        }
        else{
            User.loggedInUser.team_id = teams.get(p).id;
            Boolean success = Tables.UserTable.update(User.loggedInUser);
            if (success) Toast.makeText(getActivity(), "Joined " + teams.get(p).name, Toast.LENGTH_SHORT).show();
            else Toast.makeText(getActivity(), "You're already on a team!", Toast.LENGTH_SHORT).show();
        }

    }
}
