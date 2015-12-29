package com.foi.air.potrosko;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by Andrej on 14.11.2015..
 */
public class LoginActivity extends AppCompatActivity{
    // Pin edittext
    EditText txtPin;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //dohvacanje SharedPreferences iz ChangePinActivity klase
        SharedPreferences mSettings = getSharedPreferences("ChangePinActivity", MODE_PRIVATE);

        /*spremanje dohvacenog pina u pin varijablu
        pin treba staviti umjesto "1234" kod provjere pina nakon sto se stavi provjera je li SharedPreferences
        prazan i ako je pozvati ChangePinActivity, ako nije pozvati LoginActivity*/
        final String pin2 = mSettings.getString("etPinString", ChangePinActivity.etPinString);


        // Pin input text
        txtPin = (EditText) findViewById(R.id.input_pin);

        // Login with pressing soft keyboard key
        txtPin.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    // do your stuff here
                    // Get pin from EditText
                    String pin = txtPin.getText().toString();

                        // Check if pin is filled
                        if(pin.trim().length() > 0){
                            // For testing pin is checked with sample data
                            // pin = 1234
                            if(pin.equals(pin2)){
                                // Starting MainActivity
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                                finish();

                            }else{
                                // pin doesn't match
                                txtPin.setError("PIN is incorrect");
                            }
                        }else{
                            // user didn't entered pin
                            // Show alert asking him to enter the details
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
