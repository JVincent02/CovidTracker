package com.example.covidtracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class SplashActivity extends AppCompatActivity {

    LoginButton loginButton;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        loginButton = findViewById(R.id.splashFbCon);
        callbackManager = CallbackManager.Factory.create();
        //loginButton.setReadPermissions(Arrays.asList("default"));
        loginButton.setReadPermissions(Arrays.asList("user_location","email","user_birthday","user_gender"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("Status", "Success");
                String userID = loginResult.getAccessToken().getToken();
                goToMainAct(userID);
                finish();
            }

            @Override
            public void onCancel() {
                Log.d("Status", "Cancel");
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(SplashActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                Log.d("Status", error.toString());
            }
        });
        hideSystemUI();
        AccessToken current = AccessToken.getCurrentAccessToken();
        if(current!=null){
            goToMainAct(current.getUserId());
        }
    }
    private void goToMainAct(String userID){
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        intent.putExtra("userId", userID);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}
