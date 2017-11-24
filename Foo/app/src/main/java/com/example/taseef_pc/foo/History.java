package com.example.taseef_pc.foo;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import java.util.concurrent.ThreadLocalRandom;

public class History extends AppCompatActivity {
    EditText name, location, contact, date, id_no, hist_id;
    Button history, id, seelist;
    String  hid;
    // int ids;
    TextView idtext;

    RequestQueue requestQueue;
    private String urlHistory = "http://fajr-magazine.com/food/movie/setHistory";
    private String urlHistory2 = "http://fajr-magazine.com/food/movie/getHistory";
    Typeface tf1, tf2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        id_no = (EditText) findViewById(R.id.editText23);
        name = (EditText) findViewById(R.id.editText19);
        date = (EditText) findViewById(R.id.editText20);
        location = (EditText) findViewById(R.id.editText21);
        contact = (EditText) findViewById(R.id.editText22);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        history = (Button) findViewById(R.id.button13);
       // id = (Button) findViewById(R.id.button12);
       // idtext = (TextView) findViewById(R.id.txt);
        seelist = (Button) findViewById(R.id.buttonnn);


        tf1 = Typeface.createFromAsset(getAssets(), "Merriweather Light.ttf");
        tf2 = Typeface.createFromAsset(getAssets(), "Merriweather-Bold.ttf");
       // tf3 = Typeface.createFromAsset(getAssets(), "Merriweather-Italic.ttf");


        id_no.setTypeface(tf1);
        name.setTypeface(tf1);
        date.setTypeface(tf1);
        location.setTypeface(tf1);
        contact.setTypeface(tf1);
       // idtext.setTypeface(tf1);


        history.setTypeface(tf2);
        seelist.setTypeface(tf2);




        hist_id = (EditText) findViewById(R.id.txt11);



        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request = new StringRequest(Request.Method.POST, urlHistory, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //System.out.println(response.toString());
                        // Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        // startActivity(intent);

                        Toast.makeText(getApplication(), "Activity added successfully", Toast.LENGTH_LONG).show();


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Failed to add,check connectivity or restart app", Toast.LENGTH_LONG).show();

                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("user_id", id_no.getText().toString());
                        parameters.put("name", name.getText().toString());
                        parameters.put("location", location.getText().toString());
                        parameters.put("contact", contact.getText().toString());
                        parameters.put("date", date.getText().toString());

                        return parameters;
                    }
                };
                requestQueue.add(request);
            }
        });


        ////////////////////////////////////////////////////////////////3


        seelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //location = loc.getText().toString();
                hid = hist_id.getText().toString();
                StringRequest request = new StringRequest(Request.Method.POST, urlHistory2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            String responseString = response.toString();
                            JSONArray hisJsonArray = new JSONArray(responseString);
                            List<History_list> hisList = new ArrayList<>();
                            for (int i = 0; i < hisJsonArray.length(); i++) {
                                JSONObject hisJsonObj = (JSONObject) hisJsonArray.get(i);
                                History_list hist = new History_list();
                                hist.sethname(hisJsonObj.getString("name"));
                                hist.sethLocation(hisJsonObj.getString("location"));
                                hist.sethcontact(hisJsonObj.getString("contact"));
                                hist.sethDate(hisJsonObj.getString("date"));
                                hisList.add(hist);
                                //Toast.makeText(getApplication(), "success11", Toast.LENGTH_LONG).show();
                            }
                            Intent myintent = new Intent(History.this, SeeHistory.class);
                            myintent.putExtra("hisList", (Serializable) hisList);
                            startActivity(myintent);
                            //Toast.makeText(getApplication(), "success", Toast.LENGTH_LONG).show();

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
                        parameters.put("user_id", hid);
                        return parameters;
                    }
                };
                requestQueue.add(request);
            }
        });


    }
}
