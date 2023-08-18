package com.hoainam.unitynative.applelogin;

import android.app.Activity;
import android.content.Intent;

public class AppleLogin {
    public static void Login(Activity activity){
        Intent myIntent = new Intent(activity, AppleLoginActivity.class);
        activity.startActivity(myIntent);
    }
}
