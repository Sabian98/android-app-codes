package com.example.taseef_pc.gifdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static com.example.taseef_pc.gifdemo.R.id.viewGif;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PlayGifView pGif = (PlayGifView) findViewById(viewGif);
        pGif.setImageResource( R.drawable.ajax);

    }
}
