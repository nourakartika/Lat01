 package com.Cinta.lat01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

 public class RrgistrationActivity extends AppCompatActivity {

     EditText etfn, etbirth;
     TextView tvhasil;
     Button btexit;
     Spinner spclass;
     RadioGroup rgender;
     DatePickerDialog datePickerDialog;
     SimpleDateFormat dateFormatter;

     // Membuat nama file
     private String filename = "SampleFile.txt";
     private String filepath = "MyFileStorage";
     File myExternalFile;
     Button btsubmit2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rrgistration);
        etfn = (EditText) findViewById(R.id.etfullname);
        spclass = (Spinner) findViewById(R.id.kelas);
        rgender = (RadioGroup) findViewById(R.id.rgender);
        etbirth= (EditText) findViewById(R.id.etbirth);
        tvhasil = (TextView) findViewById(R.id.tvhasil);
        btexit = (Button) findViewById(R.id.btexit);
        dateFormatter = new SimpleDateFormat("dd-MM-YYYY");
        btsubmit2 = (Button) findViewById(R.id.btsubmit2);
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            btsubmit2.setEnabled(false);
        }
        else {
            myExternalFile = new File(getExternalFilesDir(filepath), filename);
        }
        btexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "keluar dari aplikasi", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RrgistrationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
        );
    }

    public void showName(View v) {
        try {
            String fn = "Full Name : " + etfn.getText().toString() + "\n";
            String yc = "Class : " + spclass.getSelectedItem().toString() + "\n";
            int gender = rgender.getCheckedRadioButtonId();
            RadioButton rg = (RadioButton) findViewById(gender);
            String g = "Gender : " + rg.getText().toString()+ "\n";
            String birth = "Date of Birth : " + etbirth.getText().toString()+ "\n";
            tvhasil.setText("Halo, " + fn + "Your Class : " + yc + "Your Gender : " + g + "Date of Birth : " + birth);
            FileOutputStream fos;
// Melakukan Inisialisasi nilai fos yang merupakan variabel objek
            fos = openFileOutput("register.txt", Context.MODE_PRIVATE);
// Menuliskan data ke Internal Storage
            fos.write(fn.getBytes());
            fos.write(yc.getBytes());
            fos.write(g.getBytes());
            fos.write(birth.getBytes());
            fos.close();
            Toast.makeText(this,"Data has been saved successfully", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            tvhasil.setText("Error : " + e.getMessage().toString());
        }
    }

     private void showDateDialog() {
         Calendar newCalendar = Calendar.getInstance();
         datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

             @Override
             public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                 Calendar newDate = Calendar.getInstance();
                 newDate.set(year, monthOfYear, dayOfMonth);
                 etbirth.setText(dateFormatter.format(newDate.getTime()));
             }

         }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

         datePickerDialog.show();
     }
     public void showDate(View v) {
         showDateDialog();
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

     public void showName2(View v) {
         try {
             String fn = "Full Name : " + etfn.getText().toString() + "\n";
             String yc = "Class : " + spclass.getSelectedItem().toString() + "\n";
             int gender = rgender.getCheckedRadioButtonId();
             RadioButton rg = (RadioButton) findViewById(gender);
             String g = "Gender : " + rg.getText().toString()+ "\n";
             String birth = "Date of Birth : " + etbirth.getText().toString()+ "\n";
             tvhasil.setText("Halo, " + fn + "Your Class : " + yc + "Your Gender : " + g + "Date of Birth : " + birth);
             try {
                 FileOutputStream fos = new FileOutputStream(myExternalFile);
                 fos.write(fn.getBytes());
                 fos.write(yc.getBytes());
                 fos.write(g.getBytes());
                 fos.write(birth.getBytes());
                 fos.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
             Toast.makeText(this,"Data has been saved successfully to External Storage", Toast.LENGTH_LONG).show();
         }
         catch (Exception e) {
             tvhasil.setText("FNF : " + e.getMessage().toString());
         }
     }
}