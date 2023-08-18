package com.hoainam.unitynative.applelogin;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;

import com.hoainam.unitynative.R;

import java.util.UUID;

public class AppleLoginActivity extends Activity {

    private WebView webView;
    private static final String loginUrl = "https://appleid.apple.com/auth/authorize?response_type=code&client_id=%1$s&redirect_uri=%2$s&state=%3$s&response_mode=form_post&context_uri=%4$s";

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

        String state = UUID.randomUUID().toString();
        String url = String.format(loginUrl, AppleLogin.clientId, AppleLogin.redirectUrl, state, AppleLogin.contextUrl);
        webView.loadUrl(url);
    }
}