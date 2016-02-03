package com.foi.air.potrosko.login;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.foi.air.potrosko.MainActivity;
import com.foi.air.potrosko.R;
import com.foi.air.potrosko.login.ChangePinActivity;


/**
 * Created by Andrej on 14.11.2015..
 */

/**
 * Klasa koja provjerava uneseni PIN u Login polje.
 */
public class LoginActivity extends AppCompatActivity{

    EditText txtPin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /**
         * Dohvacanje SharedPreferences iz ChangePinActivity klase
         */
        SharedPreferences mSettings = getSharedPreferences("Settings", MODE_PRIVATE);

        /**
         * Spremanje dohvacenog pina u {@code pin2} varijablu
         */
        final String pin2 = mSettings.getString("etPinString", ChangePinActivity.etPinString);

        txtPin = (EditText) findViewById(R.id.input_pin);

        /**
         * Logika za provjeravanje ispravnog pina kod logina.
         */
        txtPin.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    String pin = txtPin.getText().toString();
                        if(pin.trim().length() > 0){
                            if(pin.equals(pin2)){
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                                finish();
                            }else{
                                txtPin.setError("PIN is incorrect");
                            }
                        }else{
                            txtPin.setError("Please enter PIN");
                        }
                }
                return false;
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
