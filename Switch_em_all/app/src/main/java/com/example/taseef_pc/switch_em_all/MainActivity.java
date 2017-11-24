package com.example.taseef_pc.switch_em_all;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void btnclc(View view)
    {
        Intent intent=new Intent(this,MainLActivity.class);
        startActivity(intent);

    }
    public void btnclc2(View view)
    {
        Intent intent2=new Intent(this,Main2Activity.class);
        startActivity(intent2);
    }
    public void btn5clc(View view)
    {
        Intent intent3=new Intent(this,Main22Activity.class);
        startActivity(intent3);
    }
    public void btn6clc(View view)
    {
        Intent intent3=new Intent(this,Main23Activity.class);
        startActivity(intent3);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        switch(id)
        {
            case R.id.action_settings:
                Toast.makeText(getApplicationContext(), "This app helps to make a puzzle of the world around you. You can choose the puzzle size.Shake to add complexity.Solve the puzzle to prove your perception!!!", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

}
