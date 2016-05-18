package com.sdrcstudio.cimmission;

import android.app.Activity;
import android.app.ProgressDialog;
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
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sdrcstudio.cimmission.inc.JSONParser;
import com.sdrcstudio.cimmission.pengusaha.awal_umkm;
import com.sdrcstudio.cimmission.util.CustomHttpClient;
//import com.sdrcstudio.cimmission.util.CustomHttpClient;

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
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {
    private String[] us;
    private String[] pw;
    private String[] level;
    EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.etUsername);
        password = (EditText) findViewById(R.id.etPassword);

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

                Intent i = new Intent(getApplicationContext(), awal_umkm.class);
                startActivity(i);
                finish();

                /*--------------------------------------------------------------------------------
                    Karena loginnya udah jalan, loginnya di disable dulu, biar develop-nya cepet
                ----------------------------------------------------------------------------------
                if (isNetworkAvailabe()) {
                    new GetData()
                            .execute("http://cimmission.agittm.xyz/login.php",username.getText().toString(),password.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Periksa jaringan koneksi internet Anda.", Toast.LENGTH_LONG).show();
                    btn_login.setEnabled(true);
                }
                /**/
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

    public boolean isNetworkAvailabe() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ani = cm.getActiveNetworkInfo();

        return ani != null && ani.isConnected();
    }

    private class GetData extends AsyncTask<String, Void, String> {

        // Instansiasi class dialog
        ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        String Content;
        String Error = null;
        // membuat object class JSONObject yang digunakan untuk menangkap data
        // dengan format json
        JSONObject jObject;
        // instansiasi class ArrayList
        ArrayList<NameValuePair> data = new ArrayList<NameValuePair>();

        @Override
        protected String doInBackground(String... params) {
            try {
                data.add(new BasicNameValuePair("username", params[1]));
                data.add(new BasicNameValuePair("password", params[2]));
                Content = CustomHttpClient.executeHttpPost(
                        params[0],
                        data);
            } catch (ClientProtocolException e) {
                Error = e.getMessage();
                cancel(true);
            } catch (IOException e) {
                Error = e.getMessage();
                cancel(true);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return Content;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // menampilkan dialog pada saat proses pengambilan data dari
            // internet
            this.dialog.setMessage("Mohon tunggu..");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(String result) {
            // menutup dialog saat pengambilan data selesai
            this.dialog.dismiss();
            if (Error != null) {
                Toast.makeText(getBaseContext(), "Error Connection Internet",
                        Toast.LENGTH_LONG).show();
            } else {
                try {
                    // instansiasi kelas JSONObject

                    //Toast.makeText(MainActivity.this, Content, Toast.LENGTH_LONG).show();
                    jObject = new JSONObject(Content);
                    // mengubah json dalam bentuk array
                    JSONArray menuitemArray = jObject.getJSONArray("user");

                    // mendeskripsikan jumlah array yang bisa di tampung
                    us = new String[menuitemArray.length()];
                    pw = new String[menuitemArray.length()];
                    level = new String[menuitemArray.length()];

                    // mengisi variable array dengan data yang di ambil dari
                    // internet yang telah dibuah menjadi Array
                    for (int i = 0; i < menuitemArray.length(); i++) {
                        us[i] = menuitemArray.getJSONObject(i)
                                .getString("username").toString();
                        pw[i] = menuitemArray.getJSONObject(i)
                                .getString("password").toString();
                        level[i] = menuitemArray.getJSONObject(i)
                                .getString("level").toString();
                    }

                    if(level[0].equals("pengusaha")){
                        Intent i = new Intent(getApplicationContext(), awal_umkm.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        finish();
                    }
                    else if(level[0].equals("investor")){
                        Intent i = new Intent(getApplicationContext(), awal_umkm.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        finish();
                    }
                } catch (JSONException ex) {

                }
            }
        }
    }
}

