package com.sdrcstudio.cimmission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sdrcstudio.cimmission.inc.JSONParser;
import com.sdrcstudio.cimmission.pengusaha.awal_umkm;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressBar pb=(ProgressBar)findViewById(R.id.progressBar);
        final EditText username=(EditText)findViewById(R.id.etUsername);
        final EditText password=(EditText)findViewById(R.id.etPassword);

        Button btn_register = (Button) findViewById(R.id.btn_daftar);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, register.class);
                startActivity(intent);
            }
        });

        final Button btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_login.setEnabled(false);
                btn_login.setBackgroundColor(Color.parseColor("#ffb74a"));
                if(isNetworkAvailabe()){
                    pb.setVisibility(View.VISIBLE);
                    LoginProcess lp=new LoginProcess(
                            MainActivity.this,
                            btn_login,
                            pb,
                            "http://cimmission.agittm.xyz/login.php",
                            username.getText().toString(),
                            password.getText().toString()
                    );
                }
                else {
                    Toast.makeText(getApplicationContext(), "Periksa jaringan koneksi internet Anda.", Toast.LENGTH_LONG).show();
                    btn_login.setEnabled(true);
                    pb.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean isNetworkAvailabe(){
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ani = cm.getActiveNetworkInfo();

        return ani != null && ani.isConnected();
    }
}
class LoginProcess extends AsyncTask<String,Void,String> {
    public Activity c;
    Button disab;
    ProgressBar loading;

    public LoginProcess(Context a,Button b, ProgressBar c,String url, String user, String pass) {
        this.c=(Activity)a;
        this.disab=b;
        this.loading=c;

        this.execute(url,user,pass);
    }

    @Override
    protected String doInBackground(String... arg0) {
        String hasil="";
        // Creating HTTP client

        // Building post parameters
        // key and value pair
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("username","pengusaha"));
        nameValuePair.add(new BasicNameValuePair("password","pengusaha"));

        // Making HTTP Request
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject json = jsonParser.makeHttpRequest(
                    "http://cimmission.agittm.xyz/login.php", "POST", nameValuePair);

                // successfully received product details
                //JSONArray arr = json.getJSONArray("user") // JSON Array

                // get first product object from JSON Array
                JSONObject user = json.getJSONObject("user");

                hasil=user.getString("level");
        } catch (JSONException e) {
            // writing exception to log
            e.printStackTrace();
        }
        return hasil;
    }

    @Override
    protected void onPostExecute(String result){
        if(result.isEmpty()){
            Toast.makeText(c, "Username atau password tidak cocok.", Toast.LENGTH_LONG).show();
            disab.setEnabled(true);
            disab.setBackgroundResource(R.drawable.round_corner_accent);
            loading.setVisibility(View.INVISIBLE);
        }
        else {
            Toast.makeText(c, "Berhasil login sebagai"+result, Toast.LENGTH_LONG).show();
            disab.setEnabled(true);
            disab.setBackgroundResource(R.drawable.round_corner_accent);
            loading.setVisibility(View.INVISIBLE);
            //Intent i = new Intent(c, awal_umkm.class);
            //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //c.startActivity(i);
            //c.finish();
        }
    }
}