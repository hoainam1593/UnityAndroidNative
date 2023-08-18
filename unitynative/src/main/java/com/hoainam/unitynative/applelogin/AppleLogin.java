package com.hoainam.unitynative.applelogin;

import android.app.Activity;
import android.content.Intent;

public class AppleLogin {

    public static String clientId;
    public static String redirectUrl;
    public static String contextUrl;

    public static void Login(Activity activity, String clientId, String redirectUrl, String contextUrl){
        AppleLogin.clientId = clientId;
        AppleLogin.redirectUrl = redirectUrl;
        AppleLogin.contextUrl = contextUrl;

        Intent myIntent = new Intent(activity, AppleLoginActivity.class);
        activity.startActivity(myIntent);
    }
}
