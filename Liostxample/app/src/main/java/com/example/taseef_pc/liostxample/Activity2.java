package com.example.taseef_pc.liostxample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    String s;
    TextView e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        e= (TextView) findViewById(R.id.textView);
        Intent in = getIntent();

        s= in.getStringExtra("str");

        e.setText(s);






    }
}
