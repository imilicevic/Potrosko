package com.foi.air.potrosko;

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

    // login button
    Button btnLogin;

    // Session Manager Class
    SessionManagement session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Session Manager
        session = new SessionManagement(getApplicationContext());

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
                        if(pin.equals("1234")){
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

        // Login button
      /*  btnLogin = (Button) findViewById(R.id.btn_login);

        // Login button click event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get pin from EditText
                String pin = txtPin.getText().toString();

                // Check if pin is filled
                if(pin.trim().length() > 0){
                    // For testing pin is checked with sample data
                    // pin = 1234
                    if(pin.equals("1234")){
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
        }); */
    }
}
