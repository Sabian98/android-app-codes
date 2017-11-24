package com.example.taseef_pc.foo;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Credit extends AppCompatActivity {
    TextView tas,shah,spc,intc;
    Typeface tf1,tf2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        tas= (TextView) findViewById(R.id.textView20);
        intc= (TextView) findViewById(R.id.textView19);
        shah= (TextView) findViewById(R.id.textView21);
        spc= (TextView) findViewById(R.id.textView22);

        tf1 = Typeface.createFromAsset(getAssets(), "Merriweather Light.ttf");
        tf2 = Typeface.createFromAsset(getAssets(), "Merriweather-Bold.ttf");

        intc.setTypeface(tf2);
        tas.setTypeface(tf1);
        shah.setTypeface(tf1);
        spc.setTypeface(tf1);


    }
}
