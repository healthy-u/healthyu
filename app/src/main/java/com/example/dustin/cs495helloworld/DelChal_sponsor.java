package com.example.dustin.cs495helloworld;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static com.example.dustin.cs495helloworld.Main_sponsor.Chal_list;

public class DelChal_sponsor extends AppCompatActivity implements AdapterView.OnItemClickListener {

    List<String> cname=new ArrayList<String>();
    private ListView listView ;
    private ArrayAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_chal_sponsor);
        this.initAdapter();

        listView.setOnItemClickListener(this);
    }

    private void initAdapter(){
        cname.clear();
        for (int i=0;i<Chal_list.size;i++){
            cname.add(Chal_list.Clist.get(i).name);
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
        new AlertDialog.Builder(this).setTitle("Delete message box:")
                .setMessage("Delete?")
                .setPositiveButton("Delete",new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog,int which){
                    Chal_list.remove(x);
                    initAdapter();
                }})
                .setNegativeButton("Cancel",null)
                .create().show();



    }
    void btnback(View v){
        finish();
    }

}
