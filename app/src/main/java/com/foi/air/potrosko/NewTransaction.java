package com.foi.air.potrosko;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.Double2;
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

import butterknife.OnClick;


public class NewTransaction extends AppCompatActivity  {

    private Toolbar mToolbar;
    Button btnExp, btnInc;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transaction);

        // Toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        SetupEvenlyDistributedToolbar.setupEvenlyDistributedToolbar(getWindowManager().getDefaultDisplay(), mToolbar, R.menu.menu_new_transaction); // Calling new method for distributing icons
        setSupportActionBar(mToolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call

        final EditText keyboard = (EditText) findViewById(R.id.price);
        keyboard.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);



        btnExp = (Button) findViewById(R.id.btnExpense);
        btnInc = (Button) findViewById(R.id.btnIncome);

        btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText amount = (EditText)findViewById(R.id.price) ;
                String toCompare = amount.getText().toString();
                //click the button
                String value = amount.getText().toString();

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
                //click the button
                EditText amount = (EditText)findViewById(R.id.price) ;
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
        EditText price = (EditText) findViewById(R.id.price);
        Double amount = 0.0;
        try{
            amount = Double.parseDouble(price.getText().toString());
        }catch (Exception ex)
        {
            amount = 0.0;
        }finally {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.action_accept:

                // Sending amount value to next activity (NewTransactionCategory)
                Intent myIntent = new Intent(this, NewTransactionCategory.class);
                myIntent.putExtra("myAmount", amount.toString());

                Toast.makeText(getApplicationContext(), amount.toString(), Toast.LENGTH_SHORT).show();
                startActivity(myIntent);
                return true;
            case R.id.action_cancel:
                // Check if no view has focus & hides keyboard
                View view = this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                this.finish();
                Intent myIntent2 = new Intent(this, MainActivity.class);
                startActivity(myIntent2);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }




}
