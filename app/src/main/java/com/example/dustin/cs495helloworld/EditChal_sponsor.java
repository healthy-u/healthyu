package com.example.dustin.cs495helloworld;

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

public class EditChal_sponsor extends AppCompatActivity implements AdapterView.OnItemClickListener {

    List<String> cname=new ArrayList<String>();
    private ListView listView ;
    private ArrayAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_chal_sponsor);
        this.initAdapter();
        listView.setOnItemClickListener(this);

        Button backBtn = (Button) findViewById(R.id.btn_back);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnback(v);
            }
        });
    }


    private void initAdapter(){
        cname.clear();
        List<Challenge> challenges = Tables.ChallengeTable.findForSponsor(Sponsor.loggedInSponsor);
        for (int i=0;i<challenges.size();i++){
            cname.add(challenges.get(i).name);
        }

        listView =(ListView) findViewById(R.id.listview_chal);
        myAdapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                cname);
        listView.setAdapter(myAdapter);
    }

    public void onItemClick(AdapterView<?> arg0, View vv, int p, long id) {

        Intent n=new Intent(EditChal_sponsor.this,challenge_info_edit.class);
        n.putExtra("index",p);
        startActivityForResult(n,1);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        initAdapter();

    }

    void btnback(View v){
        finish();
    }
}
