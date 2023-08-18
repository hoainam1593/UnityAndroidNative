package com.hopper.game8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hoainam.unitynative.googlelogin.GoogleLogin;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_google_login).setOnClickListener(view -> {
            GoogleLogin.Login(this,
                    "664409133319-j7q2klmij7kdjj24p202g66c456ju367.apps.googleusercontent.com",
                    true, null,null,null);
        });
    }
}