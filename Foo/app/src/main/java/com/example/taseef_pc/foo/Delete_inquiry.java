package com.example.taseef_pc.foo;

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

public class Delete_inquiry extends AppCompatActivity {

    EditText name3, location2;
    Button delete2;
    RequestQueue queue;
    private String urlDelArray2 = "http://fajr-magazine.com/food/movie/delete_inquiry";
    Typeface tf1,tf2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_inquiry);

        name3 = (EditText) findViewById(R.id.nam);
        location2 = (EditText) findViewById(R.id.loca);

        delete2 = (Button) findViewById(R.id.del2);

        queue = Volley.newRequestQueue(getApplicationContext());

        tf1= Typeface.createFromAsset(getAssets(),"Merriweather Light.ttf");
        tf2=Typeface.createFromAsset(getAssets(),"Merriweather-Bold.ttf");


        name3.setTypeface(tf1);
        location2.setTypeface(tf1);
       // date.setTypeface(tf1);

        delete2.setTypeface(tf2);

        delete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(Request.Method.POST, urlDelArray2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplication(), "deleted successfully", Toast.LENGTH_LONG).show();
                        // System.out.println(response.toString());

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplication(), "Error occurred,restart app/check internet connectivity", Toast.LENGTH_LONG).show();
                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<>();
                        parameters.put("name", name3.getText().toString());
                        parameters.put("location", location2.getText().toString());
                        // parameters.put("date",date.getText().toString());

                        // parameters.put("date",date.getText().toString());

                        return parameters;
                    }
                };
                queue.add(request);
            }

        });

    }
}
