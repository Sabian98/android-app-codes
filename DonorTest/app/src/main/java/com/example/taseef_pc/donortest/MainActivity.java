package com.example.taseef_pc.donortest;

import android.annotation.TargetApi;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;


import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {



   // text=(EditText)findViewById(R.id.text1);
   EditText name2;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);              //  String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        setContentView(R.layout.activity_main);
        Calendar c = Calendar.getInstance();
        name2= (EditText) findViewById(R.id.text1);
        Date cDate = new Date();

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);

        String formatted = format1.format(cal.getTime());
        System.out.println(formatted);
    }
}
