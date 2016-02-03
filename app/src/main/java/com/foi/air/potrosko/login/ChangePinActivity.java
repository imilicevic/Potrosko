package com.foi.air.potrosko.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


import com.foi.air.potrosko.MainActivity;
import com.foi.air.potrosko.R;
import com.foi.air.potrosko.transactions.SetupEvenlyDistributedToolbar;


/**Klasa za unos pina od strane korisnika, provjerava
 * je li pin odgovarajuće dužine, sprema ga u SharedPreferences i
 * zatim poziva MainActivity
 */
public class ChangePinActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    public static String etPinString;
    EditText etPin;

    /** Metoda koja prikazuje grafičko sučelje iz xml datoteke
     * activity_change_pin, zamjenjuje action bar toolbarom
     * i sprema u EditText objekt vrijednost dobivenu prilikom
     * unošenja pina u EditText polje
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pin);

        // Toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        SetupEvenlyDistributedToolbar.setupEvenlyDistributedToolbar(getWindowManager().getDefaultDisplay(), mToolbar, R.menu.menu_new_transaction); // Calling new method for distributing icons
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(mToolbar);

        //dohvacanje vrijednosti iz edittext i pretvaranje u string
        etPin = (EditText) findViewById(R.id.edittxt_pin);

    }

    /** Metoda koja dodaje na toolbar gumbe za potvdu unosa
     * ili prekid unosa.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_transaction, menu);
        return true;
    }

    /** Metoda koja sprema string vrijednost pina u etPinString,
     * provjerava je li pin odgovarajuće dužine, pohranjuje vrijednost
     * pina u SharedPreferences i zatim poziva MainActivity
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // stavio sam za sad da šalje na MainActivity kad se kliknu kvačića i ikisić samo
        // da bi se mogla aplikacija koristiti, to naravno poslije treba promijeniti
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.action_accept:

                etPinString = etPin.getEditableText().toString();

                if(etPinString.trim().length() < 4){
                    etPin.setError("Please enter 4-digits PIN");
                    return false;
                }else{
                    SharedPreferences mSettings = this.getSharedPreferences("Settings", 0);
                    SharedPreferences.Editor editor = mSettings.edit();

                    //pohranjivanje vrijednosti iz EditTexta u SharedPreferences
                    editor.putString("etPinString", etPinString);
                    editor.apply();
                    Intent myIntent = new Intent(this, MainActivity.class);
                    myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(myIntent);
                    this.finish();
                    return true;
                }
                //stvaranje SharedPreference datoteke
                //SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(this);
            case R.id.action_cancel:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
