package com.example.taseef_pc.foo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.volley.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Reclist extends AppCompatActivity {

    private List<HashMap<String, String>> recplist = new ArrayList<>();
    private ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclist);

        Intent i = getIntent();
        List<Recipient> recList = (List<Recipient>) i.getSerializableExtra("recList");
        list = (ListView) findViewById(R.id.list1);


        onLoaded(recList);


    }

    public void onLoaded(List<Recipient> recList) {

        for (Recipient rec : recList) {

            HashMap<String, String> map = new HashMap<>();

            map.put("name", rec.getUserName());
            map.put("location", rec.getLocation());
            map.put("contact", rec.getContact());

            recplist.add(map);
        }

        loadListView();
    }

    private void loadListView() {

        ListAdapter adapter = new SimpleAdapter(Reclist.this, recplist, R.layout.list_item,
                new String[]{"name", "location", "contact"},
                new int[]{R.id.textView, R.id.textView2, R.id.textView3});

        list.setAdapter(adapter);
    }

}
