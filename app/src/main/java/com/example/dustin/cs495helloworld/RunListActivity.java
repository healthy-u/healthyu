package com.example.dustin.cs495helloworld;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class RunListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_list);

        SQLiteDatabase db = new Database(this).getReadableDatabase();

        List<Run> runs = Tables.RunTable.findForUser(User.loggedInUser);

        ArrayAdapter adapter = new ArrayAdapter<Run>(this, R.layout.simple_list_item_1, runs);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }
}