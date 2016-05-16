package com.sdrcstudio.cimmission.process;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sdrcstudio.cimmission.MainActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

/**
 * Created by TOSHIBA-PC on 16/05/2016.
 */

public class LoginProcess extends AsyncTask<String,Void,String> {
    public Activity c;
    Button disab;
    ProgressBar loading;

    public LoginProcess(Context a, Button dsb, ProgressBar pb) {
        this.c = (Activity) a;
        this.disab = dsb;
        this.loading = pb;
    }

    @Override
    protected String doInBackground(String... arg0) {


        try {
            String username = (String) arg0[0];
            String password = (String) arg0[1];
            String link = "http://cimmission.agittm.xyz/login.php";

            URL url = new URL(link);
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(link));
            HttpResponse response = client.execute(request);
            BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer sb = new StringBuffer("");
            String line = "";

            while ((line = in.readLine()) != null) {
                sb.append(line);
                break;
            }
            in.close();
            return sb.toString();
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (result.isEmpty()) {
            Toast.makeText(c, "Username atau password tidak cocok.", Toast.LENGTH_LONG).show();
            disab.setEnabled(true);
            //disab.setBackgroundResource(R.drawable.login);
            loading.setVisibility(View.INVISIBLE);
        } else {
            Intent i = new Intent(c, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            c.startActivity(i);
            c.finish();
        }
    }
}