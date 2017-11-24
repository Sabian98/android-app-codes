package com.example.taseef_pc.liostxample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "myact";
    EditText t1;
    Button btn;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = (EditText) findViewById(R.id.editText);


        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // making json array request
                // Intent intent = new Intent(getApplicationContext(), Activity2.class);
                str = t1.getText().toString();

                Intent myintent = new Intent(MainActivity.this, Activity2.class);

                myintent.putExtra("str", str);
                startActivity(myintent);

Log.d(TAG,"dedaed");
                // startActivity(intent);


                // Bundle bandl = new Bundle();
                // bandl.putString("message", m);
                // intent.putExtras(bandl);
                // startActivity(intent);

            }
        });



    }


}
