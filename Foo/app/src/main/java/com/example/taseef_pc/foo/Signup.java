package com.example.taseef_pc.foo;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
    EditText name, password;
    TextView idtext;
    Button signup, getid;

    RequestQueue requestQueue;
    private String urlSignArray = "http://fajr-magazine.com/food/movie/sign_up";
    // private String urlSignArray= "http://192.168.1.102/xnapi/movie/sign_up";
    Typeface tf1, tf2;
    String idss;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        idtext = (TextView) findViewById(R.id.textView14);

        signup = (Button) findViewById(R.id.button);

        getid = (Button) findViewById(R.id.button16);
        tf1 = Typeface.createFromAsset(getAssets(), "Merriweather Light.ttf");
        tf2 = Typeface.createFromAsset(getAssets(), "Merriweather-Bold.ttf");

        name.setTypeface(tf1);
       password.setTypeface(tf1);
        idtext.setTypeface(tf1);

        signup.setTypeface(tf2);
        getid.setTypeface(tf2);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = name.getText().toString();
                String pass = password.getText().toString();
                if(username != null && pass != null && !username.isEmpty() && !pass.isEmpty()) {
                    StringRequest request = new StringRequest(Request.Method.POST, urlSignArray, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //System.out.println(response.toString());
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Failed to sign up,check connectivity", Toast.LENGTH_LONG).show();
                        }
                    }) {

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> parameters = new HashMap<String, String>();

                            parameters.put("name", name.getText().toString());
                            parameters.put("password", password.getText().toString());

                            return parameters;
                        }
                    };
                    request.setShouldCache(false);
                    requestQueue.add(request);
                } else {
                    Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_LONG).show();
                }
            }
        });

        getid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Intent intent = new Intent(getApplicationContext(), Inquire.class);
                // startActivity(intent);
                // User_id u = new User_id();
                //ids++;
                // idss = Integer.toString(ids);
                int ids = (int) (Math.random() * (10000 - 1) + 1);
                idss = Integer.toString(ids);
                idtext.setText(idss);
            }
        });
    }
}
