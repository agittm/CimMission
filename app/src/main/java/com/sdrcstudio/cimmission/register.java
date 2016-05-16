package com.sdrcstudio.cimmission;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sdrcstudio.cimmission.investor.register_investor;
import com.sdrcstudio.cimmission.pengusaha.register_umkm;

public class register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btn_register_umkm = (Button) findViewById(R.id.btn_register_umkm);
        Button btn_register_investor = (Button) findViewById(R.id.btn_register_investor);

        btn_register_umkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(register.this, register_umkm.class);
                startActivity(intent);
            }
        });
        btn_register_investor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(register.this, register_investor.class);
                startActivity(intent);
            }
        });
    }

}
