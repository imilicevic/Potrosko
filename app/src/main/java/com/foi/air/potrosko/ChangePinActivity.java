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
 * Created by Andrej on 15.11.2015..
 */
public class ChangePinActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    public static String etPinString;
    EditText etPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pin);

        // Toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        SetupEvenlyDistributedToolbar.setupEvenlyDistributedToolbar(getWindowManager().getDefaultDisplay(), mToolbar, R.menu.menu_new_transaction); // Calling new method for distributing icons
        setSupportActionBar(mToolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call


        //dohvacanje vrijednosti iz edittext i pretvaranje u string
        etPin = (EditText) findViewById(R.id.edittxt_pin);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_transaction, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // stavio sam za sad da šalje na MainActivity kad se kliknu kvačića i ikisić samo
        // da bi se mogla aplikacija koristiti, to naravno poslije treba promijeniti
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.action_accept:
                etPinString = etPin.getEditableText().toString();


                //stvaranje SharedPreference datoteke
                //SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences mSettings = this.getSharedPreferences("Settings", 0);
                SharedPreferences.Editor editor = mSettings.edit();

                //pohranjivanje vrijednosti iz EditTexta u SharedPreferences
                editor.putString("etPinString", etPinString);
                editor.apply();
                Intent myIntent = new Intent(this, MainActivity.class);
                startActivity(myIntent);
                this.finish();
                return true;
            case R.id.action_cancel:
                Intent myIntent2 = new Intent(this, MainActivity.class);
                startActivity(myIntent2);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
