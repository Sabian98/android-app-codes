package com.example.taseef_pc.foo;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
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

public class MainActivity extends AppCompatActivity {

    EditText name2, pw;
    Button login, signup;
    RequestQueue requestQueue;
    Typeface tf1, tf2;
    String n, p;
    private String urlLoginArray = "http://fajr-magazine.com/food/movie/check_login";
// private String urlLoginArray = "http://192.168.1.102/xnapi/movie/check_login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        name2 = (EditText) findViewById(R.id.editText);

        pw = (EditText) findViewById(R.id.editText2);
        login = (Button) findViewById(R.id.button2);
///////////////////////////////////////////
        tf1 = Typeface.createFromAsset(getAssets(), "Merriweather Light.ttf");
        tf2 = Typeface.createFromAsset(getAssets(), "Merriweather-Bold.ttf");

        signup = (Button) findViewById(R.id.button);
        name2.setTypeface(tf1);
        pw.setTypeface(tf1);
        login.setTypeface(tf2);
        signup.setTypeface(tf2);

        /////////////////////////////////////////////////
        requestQueue = Volley.newRequestQueue(getApplicationContext());//if (userEmail != null && !userEmail.isEmpty() && !userEmail.equals("null"))

        login.setOnClickListener(new View.OnClickListener() {
            //  if(name2.getText())
            //sUsername = usernameEditText.getText().toString();
            @Override
            public void onClick(View view) {
                //
                //  String urlAddArray;

                n = name2.getText().toString();
                p = pw.getText().toString();
                if (n == null || n.isEmpty() || p == null || p.isEmpty()) {
                    Toast.makeText(MainActivity.this, "please fill up the required fields", Toast.LENGTH_LONG).show();
                } else {

                    StringRequest request = new StringRequest(Request.Method.POST, urlLoginArray, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //if ((name2.getText() != null) && (pw.getText() != null)) {
                            //  System.out.println(response.toString());
                            if (response.trim().equals("success")) {
                                //  profile();
                                Intent intent = new Intent(getApplicationContext(), Profile2.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(getApplicationContext(), "Failed to log in", Toast.LENGTH_LONG).show();

                            }


                        }
                    }, new Response.ErrorListener() {


                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //  Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String, String> parameters = new HashMap<>();
                            parameters.put("name", name2.getText().toString());
                            parameters.put("password", pw.getText().toString());
                            //  parameters.put("date",date.getText().toString());

                            return parameters;
                        }

                    };
                    request.setShouldCache(false);
                    requestQueue.add(request);
                }

            }
            // Toast.makeText(MainActivity.this, "please fill up the required fields", Toast.LENGTH_LONG).show();

            //   }


        });
        signup.setOnClickListener(new View.OnClickListener()


        {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);

            }

        });

    }


}
