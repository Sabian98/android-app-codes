package com.example.taseef_pc.splash_screen_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class signupActivity extends AppCompatActivity {
    EditText name,password;
    Button signup;
    TextView logpage;
    RequestQueue requestQueue;
    private String urlSignArray="http://192.168.1.103/xnapi/movie/sign_up";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        logpage= (TextView) findViewById(R.id.textView3);

        name= (EditText) findViewById(R.id.editText4);
        password= (EditText) findViewById(R.id.editText5);


        signup= (Button) findViewById(R.id.button);

        signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                StringRequest request = new StringRequest(Request.Method.POST, urlSignArray, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        System.out.println(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> parameters  = new HashMap<String, String>();

                        parameters.put("name",name.getText().toString());
                        parameters.put("password",password.getText().toString());

                        return parameters;
                    }
                };
                requestQueue.add(request);
            }
        });
        logpage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent i=new  Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

    }
}
