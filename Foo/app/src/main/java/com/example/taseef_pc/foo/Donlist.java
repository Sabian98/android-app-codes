package com.example.taseef_pc.foo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.R.id.list;

public class Donlist extends AppCompatActivity {
    private List<HashMap<String, String>> donorlist = new ArrayList<>();
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donlist);

        Intent i = getIntent();
        List<Donor> donList = (List<Donor>) i.getSerializableExtra("donList");
        list = (ListView) findViewById(R.id.list2);

        onLoaded(donList);
    }

    private void onLoaded(List<Donor> donList) {

        for (Donor don : donList) {

            HashMap<String, String> map = new HashMap<>();

            map.put("name", don.getname());
            map.put("location", don.getLocation());
            map.put("contact", don.getContact());
            map.put("date", don.getDate());
            map.put("person_no", don.getPerson_no());

            donorlist.add(map);
        }

        loadListView();

    }

    private void loadListView() {

        ListAdapter adapter = new SimpleAdapter(Donlist.this, donorlist, R.layout.list_item_2,
                new String[]{"name", "location", "contact","date","person_no"},
                new int[]{R.id.textView4, R.id.textView5, R.id.textView6, R.id.textView7, R.id.textView8});

        list.setAdapter(adapter);
    }
}
