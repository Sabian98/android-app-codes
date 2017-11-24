package com.example.taseef_pc.foo;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class New extends AppCompatActivity {
    Typeface tf1, tf2;
    TextView a, b, c, d, e, f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        tf1 = Typeface.createFromAsset(getAssets(), "Merriweather Light.ttf");
        tf2 = Typeface.createFromAsset(getAssets(), "Merriweather-Bold.ttf");

        a = (TextView) findViewById(R.id.textView14);
        b = (TextView) findViewById(R.id.textView13);
        c = (TextView) findViewById(R.id.textView15);
        d = (TextView) findViewById(R.id.textView16);
        e = (TextView) findViewById(R.id.textView17);
        f = (TextView) findViewById(R.id.textView18);

        a.setTypeface(tf2);
        b.setTypeface(tf2);
        e.setTypeface(tf2);


        c.setTypeface(tf1);
        f.setTypeface(tf1);
        d.setTypeface(tf1);

    }
}
