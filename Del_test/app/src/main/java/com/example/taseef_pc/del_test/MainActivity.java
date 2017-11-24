package com.example.taseef_pc.del_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    RequestQueue requestQueue;
    private String urlAddArray="http://192.168.1.101/xnapi/movie/addNewStudents";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
