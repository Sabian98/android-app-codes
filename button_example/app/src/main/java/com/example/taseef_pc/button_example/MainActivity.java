package com.example.taseef_pc.button_example;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
   Button text;
    Typeface tf1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        text= (Button) findViewById(R.id.button);
        tf1=Typeface.createFromAsset(getAssets(),"Merriweather-Italic.ttf");
          text.setTypeface(tf1);
      //Typeface copperplateGothicLight = Typeface.createFromAsset(getApplicationContext().getAssets(), "Merriweather Light.ttf");

     // text.setTypeface(copperplateGothicLight);



    }
}
