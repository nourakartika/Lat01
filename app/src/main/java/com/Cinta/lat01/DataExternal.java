package com.Cinta.lat01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class DataExternal extends AppCompatActivity {
    TextView dr;
    private String filename = "SampleFile.txt";
    private String filepath = "MyFileStorage";
    File myExternalFile;
    String myData="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_external);

        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            Toast.makeText(getApplicationContext(), "SD Card Unavailable", Toast.LENGTH_LONG).show();
        }
        else {
            myExternalFile = new File(getExternalFilesDir(filepath), filename);
            dr = (TextView)findViewById(R.id.tvDataRegister2);
            try {
                FileInputStream fis = new FileInputStream(myExternalFile);
// Membuat objek DataInputStream untuk mengambil objek file
                DataInputStream in = new DataInputStream(fis);
// Membuat objek BufferedReader untuk membaca isi file
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
// Membuat variabel penampung nilai text di file
                String strLine;
// Melakukan pengecekan text file kosong atau tidak
                while ((strLine = br.readLine()) != null) {
/* Jika file tidak kosong, variabel myData sebelumnya yang kosong
akan diisi dengan nilai text di strLine */
                    myData = myData + strLine + "\n";
                }
                in.close();
                dr.setText(myData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    // Proses pengecekan Eksternal Storage read only atau tidak
    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }
    // Proses pengecekan Eksternal Storage available atau tidak
    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

}