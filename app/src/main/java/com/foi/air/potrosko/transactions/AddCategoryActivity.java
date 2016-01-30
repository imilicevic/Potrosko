package com.foi.air.potrosko.transactions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.foi.air.potrosko.R;
import com.foi.air.potrosko.db.Category;
import com.foi.air.potrosko.db.TransactionType;

public class AddCategoryActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    EditText editTextkat = (EditText) findViewById(R.id.txtnamec);
    String imekat = editTextkat.getText().toString();
    EditText editTextopis = (EditText) findViewById(R.id.txtdescrip);
    String opiskat = editTextopis.getText().toString();

    Category category=new Category();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        // Toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        SetupEvenlyDistributedToolbar.setupEvenlyDistributedToolbar(getWindowManager().getDefaultDisplay(), mToolbar, R.menu.menu_new_transaction); // Calling new method for distributing icons
        setSupportActionBar(mToolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_transaction, menu);
        return true;
    }

    TransactionType ttype;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.action_accept:
                Intent myIntent = new Intent(this, CategoryActivity.class);
                startActivity(myIntent);
                category = new Category(imekat, opiskat,ttype);
                category.save();

                Toast.makeText(getApplicationContext(), "Uspješno dodano.", Toast.LENGTH_SHORT).show();
                this.finish();
                return true;
            case R.id.action_cancel:
                this.finish();
                /*
                Intent myIntent2 = new Intent(this, CategoryActivity.class);
                startActivity(myIntent2);
                */
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_income:
                if (checked)
                    ttype = TransactionType.getType("income");

                break;
            case R.id.radio_expence:
                if (checked)
                    ttype = TransactionType.getType("expense");

                break;
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

}
