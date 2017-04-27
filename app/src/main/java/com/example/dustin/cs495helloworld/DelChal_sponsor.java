package com.example.dustin.cs495helloworld;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DelChal_sponsor extends AppCompatActivity implements AdapterView.OnItemClickListener {

    List<String> cname=new ArrayList<String>();
    private ListView listView ;
    private ArrayAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_chal_sponsor);
        this.initAdapter();

        Button backBtn = (Button) findViewById(R.id.btn_back);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnback(v);
            }
        });


        listView.setOnItemClickListener(this);
    }

    private void initAdapter(){
        Challenge.challenges = Tables.ChallengeTable.findForSponsor(Sponsor.loggedInSponsor);

        cname.clear();
        for (int i=0;i<Challenge.challenges.size();i++){
            cname.add(Challenge.challenges.get(i).name);
        }

        listView =(ListView) findViewById(R.id.listview_chal);
        myAdapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                cname);
        listView.setAdapter(myAdapter);
    }

    public void onItemClick(AdapterView<?> arg0, View vv, int p, long id) {

        Intent n = new Intent(DelChal_sponsor.this, challenge_info.class);
        final int x = p;
        new AlertDialog.Builder(this).setTitle("Delete Challenge:")
                .setMessage("Are you sure?")
                .setPositiveButton("Delete",new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog,int which){
                    Tables.ChallengeTable.delete(Challenge.challenges.get(x));
                    initAdapter();
                }})
                .setNegativeButton("Cancel",null)
                .create().show();



    }
    void btnback(View v){
        finish();
    }

}
