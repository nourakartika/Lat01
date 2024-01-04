package com.Cinta.lat01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DataActivity extends AppCompatActivity {
    TextView dr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        dr = (TextView)findViewById(R.id.tvDataRegister);
        String filename = "register.txt";
        StringBuffer stringBuffer = new StringBuffer();
        try {

            BufferedReader inputReader = new BufferedReader(new
                    InputStreamReader(openFileInput(filename)));
            String inputString;

            while ((inputString = inputReader.readLine()) != null) {
                stringBuffer.append(inputString + "\n");
            }
            dr.setText(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}