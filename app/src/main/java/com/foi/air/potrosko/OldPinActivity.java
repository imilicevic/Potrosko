package com.foi.air.potrosko;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import com.foi.air.potrosko.transactions.SetupEvenlyDistributedToolbar;

/**
 * Created by Andrej on 2.2.2016.
 *
 */

/**
 * Koristi se kod promjene PINa.
 * Uspoređuje unešeni PIN s već postavljenim PINom.
 * Ako se podudaraju šalje na {@code ChangePinActivity()}
 */
public class OldPinActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    public static String PinString;
    EditText Pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_pin);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        SetupEvenlyDistributedToolbar.setupEvenlyDistributedToolbar(getWindowManager().getDefaultDisplay(), mToolbar, R.menu.menu_new_transaction); // Calling new method for distributing icons

        setSupportActionBar(mToolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call


        setSupportActionBar(mToolbar);

        /**
         * Dohvaća se vrijednost iz editText i pretvara u String
         */

        Pin = (EditText) findViewById(R.id.edittxt_pin);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_transaction, menu);
        return true;
    }

    /**
     * Dohvaća se SharePreferences iz {@code ChangePinActivity()}.
     * Dohvaćena vrijednost se pretvara u String.
     * Uspoređuje se s novo unešenim PINom te postupa prema pravilu.
     * @param item dohvaća ID od kvačice i iksića
     * @return vraća true ili false ovisno o uspjehu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        SharedPreferences mSettings = getSharedPreferences("Settings", MODE_PRIVATE);


        final String pin = mSettings.getString("etPinString", ChangePinActivity.etPinString);

        String pin2 = mSettings.getString("etPinString", ChangePinActivity.etPinString);


        PinString = Pin.getEditableText().toString();

        int id = item.getItemId();
        switch (id) {
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
