package com.hoainam.unitynative.applelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.hoainam.unitynative.R;

public class AppleLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apple_login);

        SetupWebview(findViewById(R.id.webview_apple_login));
    }

    private void SetupWebview(WebView webView){
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.google.com/");
    }
}