package com.hoainam.unitynative.admobump;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.android.ump.ConsentDebugSettings;
import com.google.android.ump.ConsentForm;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.ConsentRequestParameters;
import com.google.android.ump.FormError;
import com.google.android.ump.UserMessagingPlatform;
import com.unity3d.player.UnityPlayer;

//https://developers.google.com/interactive-media-ads/ump/android/quick-start

public class AdmobUMPImpl {

    private final Activity activity;
    private final String testDevice;
    private final String targetName;
    private final String successFunc;
    private final String failFunc;
    private ConsentInformation consentInfo;
    public AdmobUMPImpl(Activity activity, String testDevice, String targetName, String successFunc, String failFunc){
        this.activity = activity;
        this.testDevice = testDevice;
        this.targetName = targetName;
        this.successFunc = successFunc;
        this.failFunc = failFunc;
    }

    public void ShowConsentForm(){
        ConsentRequestParameters request = BuildConsentRequestParameters();
        consentInfo = UserMessagingPlatform.getConsentInformation(activity);
        consentInfo.requestConsentInfoUpdate(activity, request, this::onRequestConsentInfoUpdateSuccess, this::onRequestConsentInfoUpdateFail);
    }

    //region request consent info update callback

    private void onRequestConsentInfoUpdateSuccess(){
        boolean available = consentInfo.isConsentFormAvailable();
        if (available){
            LoadConsentForm();
        }else {
            FinishFailed("consent form is not available");
        }
    }

    private void onRequestConsentInfoUpdateFail(FormError error){
        FinishFailed("request consent info update fail, msg=" + error.getMessage());
    }

    //endregion

    //region load form callback

    private void onLoadFormSuccess(ConsentForm form){
        int status = consentInfo.getConsentStatus();
        if (status == ConsentInformation.ConsentStatus.REQUIRED){
            form.show(activity, this::onFormDismissed);
        }else {
            String statusAsStr = null;
            switch (status){
                case ConsentInformation.ConsentStatus.UNKNOWN:
                    statusAsStr = "UNKNOWN";
                    break;
                case ConsentInformation.ConsentStatus.NOT_REQUIRED:
                    statusAsStr = "NOT_REQUIRED";
                    break;
                case ConsentInformation.ConsentStatus.OBTAINED:
                    statusAsStr = "OBTAINED";
                    break;
            }
            FinishSuccess(statusAsStr);
        }
    }

    private void onLoadFormFail(FormError error){
        FinishFailed("load consent form fail, msg=" + error.getMessage());
    }

    //endregion

    //region show form callback

    private void onFormDismissed(@Nullable FormError error){
        LoadConsentForm();
    }

    //endregion

    //region finish form

    private void FinishSuccess(String statusCode){
        if (targetName != null){
            UnityPlayer.UnitySendMessage(targetName, successFunc, statusCode);
        }else {
            Log.d("UMP", "finish consent form successfully");
        }
    }

    private void FinishFailed(String errMsg){
        if (targetName != null){
            UnityPlayer.UnitySendMessage(targetName, failFunc, errMsg);
        }else {
            Log.e("UMP", "finish consent form fail err=" + errMsg);
        }
    }

    //endregion

    //region utils

    private ConsentRequestParameters BuildConsentRequestParameters(){
        ConsentDebugSettings debugSettings = null;
        if (testDevice != null){
            int geography = ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_EEA;
            debugSettings = new ConsentDebugSettings.Builder(activity).setDebugGeography(geography).addTestDeviceHashedId(testDevice).build();
        }
        ConsentRequestParameters.Builder builder = new ConsentRequestParameters.Builder();
        if (debugSettings != null){
            builder.setConsentDebugSettings(debugSettings);
        }
        return builder.setTagForUnderAgeOfConsent(false).build();
    }

    private void LoadConsentForm(){
        UserMessagingPlatform.loadConsentForm(activity, this::onLoadFormSuccess, this::onLoadFormFail);
    }

    //endregion
}
