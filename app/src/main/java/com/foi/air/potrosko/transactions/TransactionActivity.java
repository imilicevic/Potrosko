package com.foi.air.potrosko.transactions;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.foi.air.potrosko.MainActivity;
import com.foi.air.potrosko.R;

/**
 * Unos iznosa transakcije i odabir vrste (Income/Expense)
 */
public class TransactionActivity extends AppCompatActivity  {

    private Toolbar mToolbar;
    Button btnExp, btnInc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        SetupEvenlyDistributedToolbar.setupEvenlyDistributedToolbar(getWindowManager().getDefaultDisplay(), mToolbar, R.menu.menu_new_transaction); // Calling new method for distributing icons
        setSupportActionBar(mToolbar);

        final EditText keyboard = (EditText) findViewById(R.id.price);
        keyboard.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        btnExp = (Button) findViewById(R.id.btnExpense);
        btnInc = (Button) findViewById(R.id.btnIncome);

        /**
         * Pritiskom na gumb Income ili Expense promjena boje i predznaka iznosa
         */
        btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText amount = (EditText)findViewById(R.id.price) ;
                String toCompare = amount.getText().toString();

                if (toCompare.startsWith("-")) {
                    toCompare = toCompare.substring(1);
                    amount.setText(toCompare);
                }
                amount.setSelection(amount.getText().length());
                amount.setTextColor(getResources().getColor(R.color.green));
            }
        });

        btnExp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText amount = (EditText)findViewById(R.id.price);
                String toCompare = amount.getText().toString();

                if (toCompare.trim().length() == 0){
                    amount.getText().insert(amount.getSelectionStart(), "-");
                }
               else if(!(toCompare.trim().length() == 0)){
                    Double value = Double.parseDouble(toCompare);

                    if (value>0){
                        value = value * (-1);
                        toCompare = value.toString();
                        amount.setText(toCompare);}
                   else {
                        amount.setText(toCompare);
                    }
               }

                amount.setSelection(amount.getText().length());
                amount.setTextColor(getResources().getColor(R.color.red));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_transaction, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();
        switch (id) {
            case R.id.action_accept:


                EditText price = (EditText) findViewById(R.id.price);
                String priceStr = price.getText().toString();

                if(!isValidInput(priceStr)){
                    Toast.makeText(this, "Unesite broj sa max 7 znamenki molim!", Toast.LENGTH_LONG).show();
                    return false;
                }

                    Double amount = 0.0;
                    try {
                        amount = Double.parseDouble(price.getText().toString());
                    } catch (Exception ex) {
                        amount = 0.0;
                    }

                    // Sending amount value to next activity (CategoryActivity)
                    Intent myIntent = new Intent(this, CategoryActivity.class);
                    myIntent.putExtra("myAmount", amount.toString());

                    //Toast.makeText(getApplicationContext(), amount.toString(), Toast.LENGTH_SHORT).show();
                    startActivity(myIntent);
                    return true;


            case R.id.action_cancel:

                View view = this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

                Intent myIntent2 = new Intent(this, MainActivity.class);
                myIntent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent2);
                this.finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

    // validacija unosa
    private boolean isValidInput(String input) {
        if (input.length() > 7 || input.isEmpty()) {
            return false;
        }
        return true;
    }


}
