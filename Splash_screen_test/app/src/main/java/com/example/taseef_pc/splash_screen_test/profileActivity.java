package com.example.taseef_pc.splash_screen_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class profileActivity extends AppCompatActivity {
    Button search,donate;
    Intent intent=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        search= (Button) findViewById(R.id.button);
        donate= (Button) findViewById(R.id.button2);


        search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent=new  Intent(getApplicationContext(),searchActivity.class);
                startActivity(intent);
            }
        });

        donate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent i=new  Intent(getApplicationContext(),donateActivity.class);
                startActivity(i);
            }
        });



    }
}
