package com.example.taseef_pc.splash_screen_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class delete_donate extends AppCompatActivity {

    EditText name,location,no_person,date;
    Button delete;
    RequestQueue requestQueue;
    private String urlDeleteArray="http://192.168.1.103/xnapi/movie/deleteDonors";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_donate);


        name= (EditText) findViewById(R.id.user_name1);
        location= (EditText) findViewById(R.id.user_name2);
       no_person= (EditText) findViewById(R.id.organization);
        date=(EditText) findViewById(R.id.address);


        delete= (Button) findViewById(R.id.button);


        delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                StringRequest request = new StringRequest(Request.Method.POST, urlDeleteArray, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        System.out.println(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> parameters  = new HashMap<>();
                        parameters.put("name",name.getText().toString());
                        parameters.put("location",location.getText().toString());
                        parameters.put("no_person",no_person.getText().toString());
                        parameters.put("date",date.getText().toString());

                        // parameters.put("date",date.getText().toString());

                        return parameters;
                    }
                };
                requestQueue.add(request);


            }
        });

        //date=(EditText) findViewById(R.id.date);


    }
}
