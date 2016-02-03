package com.foi.air.potrosko;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.foi.air.potrosko.login.ChangePinActivity;
import com.foi.air.potrosko.login.LoginActivity;

/**
 * Created by Ivan on 10.11.2015..
 */

public class SplashScreenActivity extends Activity {
    private static int SPLASH_TIMER = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        //dohvacanje SharedPreferences iz ChangePinActivity klase
        SharedPreferences mSettings = getSharedPreferences("Settings", MODE_PRIVATE);

        final String pin2 = mSettings.getString("etPinString", ChangePinActivity.etPinString);

        final SharedPreferences sharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(SplashScreenActivity.this);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void  run() {
                if (pin2 != null && !pin2.isEmpty()){
                    if(sharedPrefs.getBoolean("pref_key_setpass_title", false)){
                        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                    Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(intent);
                    }
                }
                else{
                    Intent intent = new Intent(SplashScreenActivity.this, ChangePinActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        }, SPLASH_TIMER);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
