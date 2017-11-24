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

public class Delete_donate extends AppCompatActivity {
    EditText name2,person,date;
    Button delete;
    RequestQueue queue;
    private String urlDelArray="http://fajr-magazine.com/food/delete_donors";
    Typeface tf1,tf2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_donate);

        name2= (EditText) findViewById(R.id.editText11);
        person= (EditText) findViewById(R.id.editText12);
        date= (EditText) findViewById(R.id.editText13);

        delete= (Button) findViewById(R.id.button14);

        queue = Volley.newRequestQueue(getApplicationContext());


        tf1= Typeface.createFromAsset(getAssets(),"Merriweather Light.ttf");
        tf2=Typeface.createFromAsset(getAssets(),"Merriweather-Bold.ttf");


        name2.setTypeface(tf1);
        person.setTypeface(tf1);
        date.setTypeface(tf1);

        delete.setTypeface(tf2);


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(Request.Method.POST, urlDelArray, new Response.Listener<String>() {
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
                        Map<String,String> parameters  = new HashMap<>();
                        parameters.put("name",name2.getText().toString());
                        parameters.put("no_person",person.getText().toString());
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
