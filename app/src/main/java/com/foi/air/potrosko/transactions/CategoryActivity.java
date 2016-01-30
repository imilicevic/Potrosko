package com.foi.air.potrosko.transactions;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.foi.air.potrosko.MainActivity;
import com.foi.air.potrosko.R;
import com.foi.air.potrosko.db.Category;
import com.foi.air.potrosko.db.Transaction;
import com.foi.air.potrosko.db.TransactionType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        SetupEvenlyDistributedToolbar.setupEvenlyDistributedToolbar(getWindowManager().getDefaultDisplay(), mToolbar, R.menu.menu_new_transaction); // Calling new method for distributing icons
        setSupportActionBar(mToolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        // Date handling
        EditText edittext = (EditText) findViewById(R.id.txtDate);
        EditTextDatePicker datepicker = new EditTextDatePicker(this, edittext);
        datepicker.setCurrentDate();

        // Getting data from previous activity (TransactionActivity)
        List<TransactionType> transactionTypes = TransactionType.getAll();
        if(transactionTypes == null || transactionTypes.size() <= 0){
            TransactionType expense = new TransactionType("expense", "amounts spent during time period");
            TransactionType income = new TransactionType("income", "amounts received during time period");

            expense.save();
            Toast.makeText(getApplicationContext(), expense.toString(), Toast.LENGTH_SHORT).show();
            income.save();
            Toast.makeText(getApplicationContext(), income.toString(), Toast.LENGTH_SHORT).show();

            List<Category> categories = Category.getAll();
            Toast.makeText(getApplicationContext(), categories.toString(), Toast.LENGTH_LONG).show();
            if(categories == null || categories.size() <= 0) {

                // expense categories
                Category general = new Category("opće", "Općenite transakcije, nesvrstane.", expense);
                Category food = new Category("hrana", "Izdavanja na hranu.", expense);
                Category car = new Category("auto", "Sva izdavanja za automobil.", expense);
                Category travel = new Category("putovanja", "Sva izdavanja za putovanja.", expense);
                Category home = new Category("kućanstvo", "Izdavanja vezana uz kućanstvo.", expense);
                Category shopping = new Category("shopping", "Izdavanja vezana uz shopping", expense);
                Category transport = new Category("prijevoz", "Izdavanja za prijevoz.", expense);
                Category other = new Category("drugo", "Ostala nesvrstana plaćanja.", expense);

                // income categories
                Category salary = new Category("plaća", "Mjesečna naknada za rad.", income);
                Category secondIncome = new Category("drugi dohodak", "Drugi ostvareni dohodak u mjesecu.", income);
                Category gift = new Category("poklon", "Novac primljen kao poklon.", income);

                general.save();
                food.save();
                car.save();
                travel.save();
                home.save();
                shopping.save();
                transport.save();
                other.save();

                salary.save();
                secondIncome.save();
                gift.save();

                Toast.makeText(getApplicationContext(), gift.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Kategorije dodane.", Toast.LENGTH_SHORT).show();

            }
        }


        try {
            Intent intent = getIntent();
            String s = intent.getStringExtra("myAmount");
            Double amount = Double.parseDouble(s);

            //String amount = bundle.getString("amount");
            TextView txtAmount = (TextView) findViewById(R.id.txtAmount);
            txtAmount.setText(amount.toString());
            // Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();

        }catch (Exception ex){
            Toast.makeText(getApplicationContext(), "Smeće", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_transaction, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        Transaction transaction = new Transaction();
        TransactionType ttype;
        Category c;


        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.action_accept:
                try {
                    Intent intent = getIntent();
                    String s = intent.getStringExtra("myAmount");
                    Double amount = Double.parseDouble(s);

                    // setting Transaction type
                    if (amount > 0) {
                        ttype = TransactionType.getType("income");
                       // Toast.makeText(getApplicationContext(), "Prihod", Toast.LENGTH_SHORT).show();
                    } else {
                        ttype = TransactionType.getType("expense");
                       // Toast.makeText(getApplicationContext(), "Trošak", Toast.LENGTH_SHORT).show();
                    }
                    String note = ((TextView) findViewById(R.id.txtNote)).getText().toString();


                    c = Category.getCategory("auto");
                    if(c == null){
                        c = Category.getCategory("opće");
                    }
                    //Toast.makeText(getApplicationContext(), c.getName(), Toast.LENGTH_SHORT).show();
                    String dateTxt = ((TextView) findViewById(R.id.txtDate)).getText().toString();
                    Date date = Calendar.getInstance().getTime();
                    Toast.makeText(getApplicationContext(), dateTxt, Toast.LENGTH_SHORT).show();

                    DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                    DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String inputDateStr = dateTxt;
                    Date datet = inputFormat.parse(inputDateStr);
                    String outputDateStr = outputFormat.format(datet);

                    // TODO Saving to db
                   transaction = new Transaction(ttype, c, c.getName(), outputDateStr, note, amount);
                   transaction.save();

                    Toast.makeText(getApplicationContext(), "Uspješno dodano.", Toast.LENGTH_SHORT).show();
                }
                catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG).show();
                }
                finally {
                    Intent myIntent = new Intent(this, MainActivity.class);
                    startActivity(myIntent);
                    this.finish();
                    return true;

                }

            case R.id.action_cancel:
                this.finish();
                /*
                Intent myIntent2 = new Intent(this, TransactionActivity.class);
                startActivity(myIntent2);
                */
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    public void addActivity(View view) {
    // Do something in response to button
        Intent intent = new Intent(this, AddCategoryActivity.class);
        // TODO startActivityForResult
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

}
