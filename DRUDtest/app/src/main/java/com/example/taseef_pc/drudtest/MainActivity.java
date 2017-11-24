package com.example.taseef_pc.drudtest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity  {
    private EditText SID;
    private EditText SNAME;
    private EditText SDATE;

    private Button buttonAdd;
    private Button buttonView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        SID = (EditText) findViewById(R.id.IDBOX);
        SNAME = (EditText) findViewById(R.id.NAMEBOX);
        SDATE = (EditText) findViewById(R.id.DATEBOX);

        buttonAdd = (Button) findViewById(R.id.add_button);
        buttonView = (Button) findViewById(R.id.viewallButton);

        //Setting listeners to button
        buttonAdd.setOnClickListener((View.OnClickListener) this);
        buttonView.setOnClickListener((View.OnClickListener) this);
    }



       private void add_student()
        {
            final String id = SID.getText().toString().trim();
            final String name = SNAME.getText().toString().trim();
            final String date = SDATE.getText().toString().trim();

            class AddEmployee extends AsyncTask<Void,Void,String> {

                ProgressDialog loading;


                @Override
                protected String doInBackground(Void... params) {
                    HashMap<String,String> ui = new HashMap<>();
                    ui.put(Config.KEY_EMP_NAME,name);
                   ui.put(Config.KEY_EMP_ID,id);
                    ui.put(Config.KEY_EMP_DATE,date);

                    RequestHandler rh = new RequestHandler();
                    String res = rh.sendPostRequest(Config.URL_ADD,ui);
                    return res;
                }

                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    loading.dismiss();
                    Toast.makeText(MainActivity.this,s, Toast.LENGTH_LONG).show();
                }
                protected void onPreExecute() {
                    super.onPreExecute();
                    loading = ProgressDialog.show(MainActivity.this,"Adding...","Wait...",false,false);
                }



            }
            AddEmployee ae = new AddEmployee();//ITS OK HERE
            ae.execute();


            }
    public void onClick(View v) {
        if(v == buttonAdd){
            add_student();
        }

        if(v == buttonView){
            startActivity(new Intent(this,ViewAllStudent.class));
        }
    }

        }






