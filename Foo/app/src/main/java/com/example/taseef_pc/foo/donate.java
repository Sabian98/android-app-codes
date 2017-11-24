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
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class donate extends AppCompatActivity {

    Button proceed;
    private String urlEntryArray = "http://fajr-magazine.com/food/movie/addDonors";
    EditText name, location, no_person, contact, date;
    RequestQueue requestQueue;
    String str;
    Typeface tf1,tf2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        name = (EditText) findViewById(R.id.editText3);
        location = (EditText) findViewById(R.id.editText4);
        contact = (EditText) findViewById(R.id.editText5);
        no_person = (EditText) findViewById(R.id.editText6);
        date = (EditText) findViewById(R.id.editText7);
        proceed = (Button) findViewById(R.id.button6);


        tf1= Typeface.createFromAsset(getAssets(),"Merriweather Light.ttf");
        tf2=Typeface.createFromAsset(getAssets(),"Merriweather-Bold.ttf");


        name.setTypeface(tf1);
        location.setTypeface(tf1);
        date.setTypeface(tf1);
        no_person.setTypeface(tf1);
        contact.setTypeface(tf1);

        proceed.setTypeface(tf2);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Intent intent=new  Intent(getApplicationContext(),delete_donate.class);
                // startActivity(intent);

                StringRequest request = new StringRequest(Request.Method.POST, urlEntryArray, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        str = location.getText().toString();

                        Intent intent = new Intent(getApplicationContext(), Inq_del_donate.class);//SWITCH
                        intent.putExtra("str", str);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "An error occurred,check internet connectivity", Toast.LENGTH_LONG).show();
                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("name", name.getText().toString());
                        parameters.put("location", location.getText().toString());
                        parameters.put("contact", contact.getText().toString());
                        parameters.put("no_person", no_person.getText().toString());
                        parameters.put("date", date.getText().toString());

                        return parameters;
                    }
                };
                requestQueue.add(request);

            }
        });

    }
}
