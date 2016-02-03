package com.foi.air.potrosko.settings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.foi.air.potrosko.MainActivity;
import com.foi.air.potrosko.R;


/** Klasa koja provjerava koji je radio gumb odabran
 * i na temelju njega mijenja polja za tekst valute u glavnoj listi
 * i kod upisa nove transakcije
 */
public class ChooseCurrency extends AppCompatActivity {

    private RadioGroup radiogroup;
    private RadioButton radioButtonUsd;
    private RadioButton radioButtonHrk;

    TextView textViewToChange;
    TextView textViewToChangeTransaction;

    /** Metoda koja prikazuje grafičko sučelje
     * koje je definirano u xml datoteci od aktivnosti.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_currency);
    }

    /** Metoda koja provjerava koji je radio gumb odabran
     * i na temelju njega mijenja polja za tekst valute u glavnoj listi
     * i kod upisa nove transakcije
     */
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        textViewToChange = (TextView) findViewById(R.id.text_valute);
        textViewToChangeTransaction = (TextView) findViewById(R.id.valute);

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_dolari:
                if (checked) {

                    //error zbog setText metode
                    textViewToChange.setText("USD");
                    textViewToChangeTransaction.setText("USD");
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                    break;
                }
            case R.id.radio_kune:
                if (checked){
                    textViewToChange.setText("HRK");
                    textViewToChangeTransaction.setText("HRK");
                    Intent i2 = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i2);
                    finish();
                    break;
                }
                //nakon minimiziranja aplikacije i ponovnog pokretanja iz memorije ce odabir
                //otici na prazan ekran, a ne mainactivity, treba razrijesiti to s lifecycle metodama
        }
    }
}