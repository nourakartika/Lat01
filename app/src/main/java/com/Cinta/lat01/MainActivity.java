package com.Cinta.lat01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button bt_logout;
    TextView nama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_logout = (Button) findViewById(R.id.bt_logout);
        nama = (TextView) findViewById(R.id.tv_namaMain);
    }
    public void openRegister(View v) {
        Intent intent = new Intent(MainActivity.this, RrgistrationActivity.class);
        startActivity(intent);
    }
    public void showRegister(View v) {
        Intent intent = new Intent(MainActivity.this, DataActivity.class);
        startActivity(intent);
    }

    public void showRegister2(View v) {
        Intent intent = new Intent(MainActivity.this, DataExternal.class);
        startActivity(intent);
    }


}