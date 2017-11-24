package com.example.taseef_pc.foo;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inq_del_rcv extends AppCompatActivity {             //WILL SEARCH FOR DONORS
    Button inquire,delete;
   private String urlDonate = "http://fajr-magazine.com/food/movie/date_list";
 // private String urlDonate= "http://192.168.0.106/xnapi/movie/date_list";
    String location;
    RequestQueue queue;
    Typeface tf2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inq_del_rcv);

        inquire = (Button) findViewById(R.id.button11);

        Intent in = getIntent();

        delete= (Button) findViewById(R.id.button10);

        location = in.getStringExtra("str");
        queue = Volley.newRequestQueue(getApplicationContext());
        tf2 = Typeface.createFromAsset(getAssets(), "Merriweather-Bold.ttf");

        inquire.setTypeface(tf2);
        delete.setTypeface(tf2);


        inquire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getApplication(), "Error occurred2", Toast.LENGTH_LONG).show();
                //location = loc.getText().toString();
                StringRequest request = new StringRequest(Request.Method.POST, urlDonate, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("error", "on response");
                            String responseString = response.toString();
                            JSONArray donJsonArray = new JSONArray(responseString);
                            List<Donor> donList = new ArrayList<>();
                            for (int i = 0; i < donJsonArray.length(); i++) {
                                JSONObject donJsonObj = (JSONObject) donJsonArray.get(i);
                                Donor don = new Donor();
                                don.setname(donJsonObj.getString("name"));
                                don.setLocation(donJsonObj.getString("location"));
                                don.setcontact(donJsonObj.getString("contact"));
                                don.setDate(donJsonObj.getString("date"));
                                don.setPerson_no(donJsonObj.getString("no_person"));
                                donList.add(don);
                            }
                            Intent myintent = new Intent(Inq_del_rcv.this, Donlist.class);
                            myintent.putExtra("donList", (Serializable) donList);
                            startActivity(myintent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplication(), "Error occurred3", Toast.LENGTH_LONG).show();

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // Log.e("Error", error.getMessage());
                       // Toast.makeText(getApplication(), "Error occurred", Toast.LENGTH_LONG).show();

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
                        Map<String, String> parameters = new HashMap<>();
                        parameters.put("location", location);
                        return parameters;
                    }
                };
                queue.add(request);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Delete_inquiry.class);
                startActivity(intent);
            }
        });


    }
}
