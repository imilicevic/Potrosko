package com.foi.air.potrosko;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.EditText;


import com.foi.air.potrosko.fragments.HomeScreenFragment;
import com.foi.air.potrosko.transactions.CategoryActivity;
import com.foi.air.potrosko.transactions.SetupEvenlyDistributedToolbar;

/**
 * Created by Andrej on 2.2.2016..
 */
public class OldPinActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    public static String PinString;
    EditText Pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_pin);

        // Toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        SetupEvenlyDistributedToolbar.setupEvenlyDistributedToolbar(getWindowManager().getDefaultDisplay(), mToolbar, R.menu.menu_new_transaction); // Calling new method for distributing icons
        setSupportActionBar(mToolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call




        //dohvacanje vrijednosti iz edittext i pretvaranje u string
        Pin = (EditText) findViewById(R.id.edittxt_pin);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_transaction, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //dohvacanje SharedPreferences iz ChangePinActivity klase
        SharedPreferences mSettings = getSharedPreferences("Settings", MODE_PRIVATE);


        final String pin2 = mSettings.getString("etPinString", ChangePinActivity.etPinString);

        PinString = Pin.getEditableText().toString();

        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.action_accept:

                    if(!PinString.equals(pin2)){
                        Pin.setError("PIN doesn't match old PIN");
                        return false;

                    }else{
                        Intent myIntent = new Intent(this, ChangePinActivity.class);
                        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(myIntent);
                        this.finish();
                        return true;
                    }



            case R.id.action_cancel:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
