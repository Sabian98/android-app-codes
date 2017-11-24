package com.example.taseef_pc.foo;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Profile2 extends AppCompatActivity {
    Button donate, inquire, history, about, credit;
    Typeface tf2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_profile2);


        donate = (Button) findViewById(R.id.button5);
        inquire = (Button) findViewById(R.id.button4);
        history = (Button) findViewById(R.id.button3);
        about = (Button) findViewById(R.id.button15);
        credit = (Button) findViewById(R.id.button12);


        tf2 = Typeface.createFromAsset(getAssets(), "Merriweather-Bold.ttf");

        donate.setTypeface(tf2);
        inquire.setTypeface(tf2);
        history.setTypeface(tf2);
        about.setTypeface(tf2);
        credit.setTypeface(tf2);


        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), donate.class);
                startActivity(intent);
            }
        });


        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), History.class);
                startActivity(intent);
            }
        });
        inquire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Inquire.class);
                startActivity(intent);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), New.class);
                startActivity(intent);
            }
        });

        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Credit.class);
                startActivity(intent);
            }
        });

        //about.setOnClickListener(new View.OnClickListener() {
        //@Override
        // public void onClick(View v) {

        //Intent intent = new Intent(getApplicationContext(), Dapp.class);
        // startActivity(intent);
        //      }
        // });
    }

}
