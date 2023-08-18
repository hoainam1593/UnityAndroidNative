package com.hoainam.unitynative.applelogin;

import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AppleLoginWebClient extends WebViewClient {

    //by default, when user click a link, it will open default browser in device
    //override this to keep user still in app's webview
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.e("AppleLogin", url);
        return false;
    }
}
