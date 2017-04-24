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

import java.util.ArrayList;
import java.util.List;

import static com.example.dustin.cs495helloworld.MainActivity.CRlst;

/**
 * Created by lulu on 2017-04-06.
 */

public class campus extends Fragment implements AdapterView.OnItemClickListener {
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
        for(int i=0;i<CRlst.size; i++){
            cname.add(CRlst.getname(i));
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
