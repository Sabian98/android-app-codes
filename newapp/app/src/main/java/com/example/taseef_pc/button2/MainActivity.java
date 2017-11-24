package com.example.taseef_pc.button2;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
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

public class MainActivity extends AppCompatActivity {

    Button inq;
    EditText loc;
    private String urlDate = "http://192.168.0.103/xnapi/movie/date_list";
    String location;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inq = (Button) findViewById(R.id.button2);
        loc = (EditText) findViewById(R.id.loc);
        final String TAG = "MyActivity";

        queue = Volley.newRequestQueue(getApplicationContext());
        inq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = loc.getText().toString();
                StringRequest request = new StringRequest(Request.Method.POST, urlDate, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            String responseString = response.toString();
                            JSONArray donorJsonArray = new JSONArray(responseString);
                            List<Donor> donorList = new ArrayList<>();
                            for (int i = 0; i < donorJsonArray.length(); i++) {
                                JSONObject donorJsonObj = (JSONObject) donorJsonArray.get(i);
                                Donor donor = new Donor();
                                donor.setUserName(donorJsonObj.getString("username"));
                                donor.setLocation(donorJsonObj.getString("location"));
                                donor.setsDate(donorJsonObj.getString("s_date"));
                                donorList.add(donor);
                            }
                            Intent myintent = new Intent(MainActivity.this, Donorlist.class);
                            myintent.putExtra("donorList", (Serializable) donorList);
                            startActivity(myintent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.getMessage());
                        Toast.makeText(getApplication(), "Error occurred", Toast.LENGTH_LONG).show();
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
    }
}