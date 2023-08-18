package com.hoainam.unitynative.applelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;

import com.hoainam.unitynative.R;

public class AppleLoginActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apple_login);

        webView = findViewById(R.id.webview_apple_login);

        SetupWebview();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void SetupWebview(){
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new AppleLoginWebClient());
        webView.loadUrl("https://www.google.com/");
    }
}