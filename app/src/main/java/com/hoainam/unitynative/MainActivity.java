package com.hoainam.unitynative;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hoainam.unitynative.admobump.AdmobUMP;
import com.hoainam.unitynative.applelogin.AppleLogin;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_apple_login).setOnClickListener(view -> {

            AdmobUMP.ShowConsentForm(this, "9176C6C1A3DBB750F930F93CFE6434B9");

//            AppleLogin.Login(this,
//                    "com.mobirix.mbgk.web",
//                    "https://girls-knightage-6403208.firebaseapp.com/__/auth/handler",
//                    "file://asset/www/index.html?apn=com.mobirix.mbgk");
        });
    }
}