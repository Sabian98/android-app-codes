package com.example.taseef_pc.login_test;


        import android.content.Context;
        import android.content.Intent;
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
public class MainActivity extends AppCompatActivity {
    EditText name2,pw;
    Button login;
    RequestQueue requestQueue;
    private String urlLoginArray="http://192.168.1.101/xnapi/movie/check_login";  //locates the related function
    // private String not=
    Context context  = null;
    CharSequence text = "Access denied!";
    int duration = Toast.LENGTH_LONG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name2 = (EditText) findViewById(R.id.name2);

        pw= (EditText) findViewById(R.id.password);
        login= (Button) findViewById(R.id.login);

        context = this.getApplication().getApplicationContext();
        requestQueue= Volley.newRequestQueue(getApplicationContext());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  String urlAddArray;
                StringRequest request = new StringRequest(Request.Method.POST, urlLoginArray, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //  System.out.println(response.toString());
                        if(response.trim().equals("success"))
                        {
                            profile();
                        }
                        else
                        {
                            Toast.makeText(context,text,duration);

                        }
                    }
                }, new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //  Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> parameters  = new HashMap<>();
                        parameters.put("name",name2.getText().toString());
                        parameters.put("password",pw.getText().toString());
                        //  parameters.put("date",date.getText().toString());

                        return parameters;
                    }
                };
                requestQueue.add(request);
            }

        });




    }
    void profile()

    {
        Intent intent=new  Intent(getApplicationContext(),AddActivity.class);
        startActivity(intent);
    }
}