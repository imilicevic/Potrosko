package com.foi.air.potrosko;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

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
                sharedPrefs.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
                    @Override
                    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                        boolean test = sharedPrefs.getBoolean("pref_key_setpass_title", false);
                    }
                });


        new Handler().postDelayed(new Runnable() {

            @Override
            public void  run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                /*if(sharedPrefs.getBoolean("pref_key_setpass_title", false)){
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                }*/

                if (pin2 != null && !pin2.isEmpty()){
                    //starting LoginActivity
                    Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else{
                    //starting ChangePinActivity
                    Intent intent = new Intent(SplashScreenActivity.this, ChangePinActivity.class);
                    startActivity(intent);
                }
                // close this activity
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
