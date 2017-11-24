package com.example.taseef_pc.foo;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Inquire extends AppCompatActivity {
    Button proceed;
   // private String urlEntryArray2 = "http://192.168.0.106/xnapi/movie/addInqs";
  private String urlEntryArray2 = "http://fajr-magazine.com/food/movie/addInqs";

    EditText name, location, contact;
    RequestQueue requestQueue;
    String str;
    Typeface tf1,tf2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquire);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        name = (EditText) findViewById(R.id.editText10);
        location = (EditText) findViewById(R.id.editText9);
        contact = (EditText) findViewById(R.id.editText8);
        proceed = (Button) findViewById(R.id.button9);


        tf1= Typeface.createFromAsset(getAssets(),"Merriweather Light.ttf");
        tf2=Typeface.createFromAsset(getAssets(),"Merriweather-Bold.ttf");


        name.setTypeface(tf1);
        contact.setTypeface(tf1);
        location.setTypeface(tf1);

        proceed.setTypeface(tf2);


        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Intent intent=new  Intent(getApplicationContext(),delete_donate.class);
                // startActivity(intent);

                StringRequest request = new StringRequest(Request.Method.POST, urlEntryArray2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        str = location.getText().toString();

                        Intent intent = new Intent(getApplicationContext(), Inq_del_rcv.class);//SWITCH
                        intent.putExtra("str", str);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                      //  Toast.makeText(getApplicationContext(), "An error occurred,check internet connectivity", Toast.LENGTH_LONG).show();

                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            Toast.makeText(getApplicationContext(),
                                    "time/connection",
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(getApplicationContext(),
                                    "authfailure",
                                    Toast.LENGTH_LONG).show();
                            //TODO
                        } else if (error instanceof ServerError) {
                            Toast.makeText(getApplicationContext(),
                                    "server",
                                    Toast.LENGTH_LONG).show();
                            //TODO
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(getApplicationContext(),
                                    "network",
                                    Toast.LENGTH_LONG).show();
                            //TODO
                        } else if (error instanceof ParseError) {
                            Toast.makeText(getApplicationContext(),
                                    "parse",
                                    Toast.LENGTH_LONG).show();
                            //TODO
                        }


                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("name", name.getText().toString());
                        parameters.put("location", location.getText().toString());
                        parameters.put("contact", contact.getText().toString());
                        // parameters.put("no_person", no_person.getText().toString());
                        // parameters.put("date", date.getText().toString());

                        return parameters;
                    }
                };
                requestQueue.add(request);

            }
        });


    }
}
