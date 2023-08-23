package com.hoainam.unitynative.admobump;

import android.app.Activity;

public class AdmobUMP {
    public static void ShowConsentForm(Activity activity, String testDevice){
        AdmobUMPImpl impl = new AdmobUMPImpl(activity, testDevice);
        impl.ShowConsentForm();
    }
}
