package com.hoainam.unitynative.admobump;

import android.app.Activity;

public class AdmobUMP {
    public static void ShowConsentForm(Activity activity, String testDevice, String targetName, String successFunc, String failFunc){
        AdmobUMPImpl impl = new AdmobUMPImpl(activity, testDevice, targetName, successFunc, failFunc);
        impl.ShowConsentForm();
    }
}
