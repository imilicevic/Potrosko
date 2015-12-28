package com.foi.air.potrosko;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.EditText;


/**
 * Created by Andrej on 15.11.2015..
 */
public class ChangePinActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pin);

        //Toolbar
        //mToolbar = (Toolbar) findViewById(R.id.toolbar);

        //SetupEvenlyDistributedToolbar.setupEvenlyDistributedToolbar(getWindowManager().getDefaultDisplay(), mToolbar, R.menu.menu_new_transaction); // Calling new method for distributing icons
        //setSupportActionBar(mToolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call


        //dohvacanje vrijednosti iz edittext i pretvaranje u string
        EditText etPin = (EditText) findViewById(R.id.edittxt_pin);
        String etPinString= etPin.getEditableText().toString();

        //stvaranje SharedPreference datoteke
        //SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences mSettings = this.getSharedPreferences("Settings", 0);
        SharedPreferences.Editor editor = mSettings.edit();

        //pohranjivanje vrijednosti iz EditTexta u SharedPreferences
        editor.putString("etPinString", etPinString);
        editor.commit();



    }

}
