package com.example.taseef_pc.button2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Donorlist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donorlist);

        Intent i = getIntent();
        List<Donor> donorList = (List<Donor>) i.getSerializableExtra("donorList");


        String fromArray[] = {"username", "location", "s_date"};//name rows for each individual row of a listview
        int to[] = {R.id.textView, R.id.textView2, R.id.textView3};//ID of each view in which the result will be displayed
        ListView listView2 = (ListView) findViewById(R.id.listview);
        SimpleAdapter simpleAdpt = new SimpleAdapter(getApplicationContext(), (List<? extends Map<String, ?>>) donorList, R.layout.list_item_22, fromArray, to);
        listView2.setAdapter(simpleAdpt);


    }
}
