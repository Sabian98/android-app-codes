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

public class searchActivity extends AppCompatActivity {
EditText name,location,conatct;
    Button search;
    private String urlEntryArray2="http://192.168.1.103/xnapi/movie/addSearcher";


    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        name= (EditText) findViewById(R.id.user_name);
        location= (EditText) findViewById(R.id.organization);
        conatct= (EditText) findViewById(R.id.address);

        search= (Button) findViewById(R.id.button);



        search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
               // Intent intent=new  Intent(getApplicationContext(),delete_donate.class);
               // startActivity(intent);

                do_search();
                do_entry();




            }
        });
    }



    void do_search()
    {



    }

    void do_entry()
    {

        StringRequest request = new StringRequest(Request.Method.POST, urlEntryArray2, new Response.Listener<String>() {
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
                Map<String,String> parameters  = new HashMap<String, String>();
                parameters.put("name",name.getText().toString());
                parameters.put("location",location.getText().toString());
                parameters.put("contact",conatct.getText().toString());
               // parameters.put("no_person",no_person.getText().toString());
               // parameters.put("date",date.getText().toString());
//
                return parameters;
            }
        };
        requestQueue.add(request);


    }


}
