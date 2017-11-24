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

public class Inq_del_donate extends AppCompatActivity {//WILL SEARCH FOR FOOD INQUIRERS
    Button inquire,delete;
  private String urlRec = "http://fajr-magazine.com/food/movie/date_list_2";
  // private String urlRec= "http://192.168.0.106/xnapi/movie/date_list_2";
    String location;
    RequestQueue queue;
    Typeface tf2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inq_del_donate);

        inquire = (Button) findViewById(R.id.button7);
        delete= (Button) findViewById(R.id.button8);

        Intent in = getIntent();

        location = in.getStringExtra("str");
        queue = Volley.newRequestQueue(getApplicationContext());


        tf2 = Typeface.createFromAsset(getAssets(), "Merriweather-Bold.ttf");

        inquire.setTypeface(tf2);
        delete.setTypeface(tf2);

        inquire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //location = loc.getText().toString();
                StringRequest request = new StringRequest(Request.Method.POST, urlRec, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            String responseString = response.toString();            //JSONObject obj = new JSONObject(result.toString());
                            // JSONArray arr = obj.getJSONArray("value");
                            JSONArray recJsonArray = new JSONArray(responseString);
                            List<Recipient> recList = new ArrayList<>();
                            for (int i = 0; i < recJsonArray.length(); i++) {
                                JSONObject recJsonObj = (JSONObject) recJsonArray.get(i);
                                Recipient rec = new Recipient();
                                rec.setname(recJsonObj.getString("name"));
                                rec.setLocation(recJsonObj.getString("location"));
                                rec.setcontact(recJsonObj.getString("contact"));
                                recList.add(rec);
                            }
                            Intent myintent = new Intent(Inq_del_donate.this, Reclist.class);
                            myintent.putExtra("recList", (Serializable) recList);
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



        ///////////////////////////////////////////////
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Delete_donate.class);
                startActivity(intent);
            }
        });


    }
}
