package com.hoainam.unitynative.googlelogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.unity3d.player.UnityPlayer;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;

public class LegacyGoogleLoginActivity extends Activity {

    //region data members

    private static final int RC_SIGN_IN = 2;
    private GoogleSignInClient mGoogleSignInClient;

    //endregion

    //region activity functions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGoogleSignInClient = GoogleSignIn.getClient(this, BuildGoogleSignInOptions());
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                GoogleSignInAccount account = task.getResult(ApiException.class);
                String token = account.getIdToken();
                FinishSuccess(token);
            } catch (ApiException e){
                String codeStr = GoogleSignInStatusCodes.getStatusCodeString(e.getStatusCode());
                String msg = String.format("login fail code=%s msg=%s", codeStr, e.getMessage());
                FinishFailed(msg);
            }
        }
    }

    //endregion

    //region finish activity

    private void FinishSuccess(String token){
        mGoogleSignInClient.signOut();
        finish();
        UnityPlayer.UnitySendMessage(GoogleLogin.callbackTargetName, GoogleLogin.callbackSuccessFunc, token);
    }

    private void FinishFailed(String errMsg){
        mGoogleSignInClient.signOut();
        finish();
        UnityPlayer.UnitySendMessage(GoogleLogin.callbackTargetName, GoogleLogin.callbackFailFunc, errMsg);
    }

    //endregion

    //region utils

    private static GoogleSignInOptions BuildGoogleSignInOptions(){
        String clientId = GoogleLogin.webClientId;
        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(clientId).requestEmail().build();
    }

    //endregion
}