package com.Cinta.lat01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    private EditText mViewUser, mViewPassword;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /* Melakukan Inisialisasi variabel dengan nilai dari UI*/
        mViewUser=findViewById(R.id.et_username);
        mViewPassword =findViewById(R.id.et_password);

        // Check if UserResponse is Already Logged In
        if(Preferences.getLoggedInStatus(getApplicationContext())) {
            Intent intent = new Intent(getApplicationContext(),
                    MainActivity.class);
            startActivity(intent);
    }
}

    /* Melakukan pegecekan inputan Username dan Password dan Memberikan akses ke
    MainActivity */
    private void razia(){
        /* Mereset semua Error dan fokus menjadi default */
        mViewUser.setError(null);
        mViewPassword.setError(null);
        View fokus = null;
        boolean cancel = false;
        /* Mengambil nilai dari UI dengan variable baru bertipe String*/
        String user = mViewUser.getText().toString();
        String password = mViewPassword.getText().toString();
        /* Pengecekan jika UI komponen username tidak diisi user*/
        if (TextUtils.isEmpty(user)){
            mViewUser.setError("This field is required");
            fokus = mViewUser;
            cancel = true;
        }else if(!cekUser(user)){
            mViewUser.setError("This Username is not found");
            fokus = mViewUser;
            cancel = true;
        }
        /* Pengecekan jika UI komponen password tidak diisi user*/
        if (TextUtils.isEmpty(password)){
            mViewPassword.setError("This field is required");
            fokus = mViewPassword;
            cancel = true;
        }else if (!cekPassword(password)){
            mViewPassword.setError("This password is incorrect");
            fokus = mViewPassword;
            cancel = true;
        }
        /* Jika cancel true, variable fokus mendapatkan fokus */
        if (cancel)
            fokus.requestFocus();
        else
            masuk();
        }

    /* Menuju ke MainActivity dan Set User dan Status menjadi sedang login di
Preferences */
    private void masuk(){
        Preferences.setLoggedInUser(getBaseContext(),Preferences.getRegisteredUser(getBaseContext()));
        Preferences.setLoggedInStatus(getBaseContext(),true);
        startActivity(new Intent(getBaseContext(),MainActivity.class));
        finish();
    }
    /* True jika parameter password sama dengan data password yang terdaftar dari
    Preferences */
    private boolean cekPassword(String password){
        return password.equals(Preferences.getRegisteredPass(getBaseContext()));
    }
    /* True jika parameter user sama dengan data user yang terdaftar dari
    Preferences */
    private boolean cekUser(String user){
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }
    public void signin(View v){
        razia();
    }
    public void signup(View v){
        startActivity(new Intent(getBaseContext(), RegisterActivity.class));
    }
}