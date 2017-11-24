package com.example.taseef_pc.foo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SeeHistory extends AppCompatActivity {

    private List<HashMap<String, String>> hlist= new ArrayList<>();
    private ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_history);


        Intent i = getIntent();
        List<History_list> list1 = (List<History_list>) i.getSerializableExtra("hisList");
        list = (ListView) findViewById(R.id.list3);
        //Toast.makeText(getApplication(), "success2", Toast.LENGTH_LONG).show();

        onLoaded2(list1);

    }

    public void onLoaded2(List<History_list> list1) {


        for (History_list his: list1) {

            HashMap<String, String> map = new HashMap<>();

            map.put("name", his.gethname());
            map.put("location", his.gethLocation());
            map.put("contact", his.gethContact());
            map.put("date", his.gethDate());

            hlist.add(map);
            //Toast.makeText(getApplication(), "success4", Toast.LENGTH_LONG).show();
        }

        loadListView();
    }

    private void loadListView() {

        ListAdapter adapter = new SimpleAdapter(SeeHistory.this, hlist, R.layout.list_item_3,
                new String[]{"name", "location", "contact","date"},
                new int[]{R.id.textView9, R.id.textView10, R.id.textView11, R.id.textView12});
       // Toast.makeText(getApplication(), "success3", Toast.LENGTH_LONG).show();

        list.setAdapter(adapter);

    }
}
