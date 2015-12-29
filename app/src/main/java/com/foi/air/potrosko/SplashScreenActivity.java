package com.foi.air.potrosko;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * Created by Ivan on 10.11.2015..
 */

public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        //dohvacanje SharedPreferences iz ChangePinActivity klase
        SharedPreferences mSettings = getSharedPreferences("ChangePinActivity", MODE_PRIVATE);

        /*spremanje dohvacenog pina u pin varijablu
        pin treba staviti umjesto "1234" kod provjere pina nakon sto se stavi provjera je li SharedPreferences
        prazan i ako je pozvati ChangePinActivity, ako nije pozvati LoginActivity*/
        final String pin2 = mSettings.getString("etPinString", ChangePinActivity.etPinString);


        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    if (pin2 != null && !pin2.isEmpty()){
                        //starting LoginActivity
                        Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    else {
                        //starting ChangePinActivity
                        Intent intent = new Intent(SplashScreenActivity.this, ChangePinActivity.class);
                        startActivity(intent);
                    }
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}
