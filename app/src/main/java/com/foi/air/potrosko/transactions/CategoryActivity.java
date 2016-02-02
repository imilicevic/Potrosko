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
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.foi.air.potrosko.MainActivity;
import com.foi.air.potrosko.R;
import com.foi.air.potrosko.db.Category;
import com.foi.air.potrosko.db.Transaction;
import com.foi.air.potrosko.db.TransactionType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
                Category general = new Category("General", "Općenite transakcije, nesvrstane.", expense, "barbershop");
                Category food = new Category("Food", "Izdavanja na hranu.", expense, "food");
                Category car = new Category("Car", "Sva izdavanja za automobil.", expense, "car");
                Category travel = new Category("Travel", "Sva izdavanja za putovanja.", expense, "car");
                Category home = new Category("House", "Izdavanja vezana uz kućanstvo.", expense, "ingredients");
                Category shopping = new Category("Shopping", "Izdavanja vezana uz shopping", expense, "clothes");
                Category transport = new Category("Transport", "Izdavanja za prijevoz.", expense, "car");
                Category other = new Category("Other", "Ostala nesvrstana plaćanja.", expense, "mobile");

                // income categories
                Category salary = new Category("Salary", "Mjesečna naknada za rad.", income, "party");
                Category secondIncome = new Category("Other income", "Drugi ostvareni dohodak u mjesecu.", income, "car");
                Category gift = new Category("Gift", "Novac primljen kao poklon.", income, "mobile");

                general.setCategoryImg("barbershop");
                general.save();
                food.setCategoryImg("food");
                food.save();
                car.save();
                travel.save();
                home.save();
                shopping.setCategoryImg("shopping");
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
        int id= 0;
        Intent inte = getIntent();
        String str = inte.getStringExtra("id");
        try {
            id = Integer.parseInt(str);
        }
        catch (Exception ex){}

        if(id > 0){

            Intent myIntent = getIntent();
            if (myIntent.hasExtra("amount")) {
                TextView mText = (TextView) findViewById(R.id.txtAmount);
                mText.setText(myIntent.getStringExtra("amount"));
            }
            if (myIntent.hasExtra("category")) {
                Spinner mSpin = (Spinner) findViewById(R.id.spinner);
                //TODO srediti postavljanje kategorije
            }
            if (myIntent.hasExtra("note")) {
                TextView mText = (TextView) findViewById(R.id.txtNote);
                mText.setText(myIntent.getStringExtra("note"));
            }
            if (myIntent.hasExtra("date")) {
                TextView mText = (TextView) findViewById(R.id.txtDate);
                mText.setText(myIntent.getStringExtra("date"));
            }

        }
       else {
            try {
                Intent intent = getIntent();
                String s = intent.getStringExtra("myAmount");
                Double amount = Double.parseDouble(s);

                //String amount = bundle.getString("amount");
                TextView txtAmount = (TextView) findViewById(R.id.txtAmount);
                txtAmount.setText(amount.toString());
                // Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }

            catch (Exception ex){
                            }

        }


        //kreiranje spinnera i dodavanje item-a
        Spinner spinner;

        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayList<String> list = new ArrayList<String>();


        List<Category> AllCategorie ;
        AllCategorie =  (List<Category>) Category.getAll();
        String type = "";


 //Sortiranje spinnera u odnosnu na odabir Income/Expense
    TextView txtAmount = (TextView) findViewById(R.id.txtAmount);
    String value = txtAmount.getText().toString();
        try{ Double amount = Double.parseDouble(value);

        if (amount < 0) {
            type = "expense";
            for (int i = 0; i < AllCategorie.size(); i++) {
                String val = AllCategorie.get(i).getName().toString();
                String ttype = AllCategorie.get(i).getTransactionType().getName().toString();
                if (ttype.equals(type))
                    list.add(new String(val));
            }
        } else {
            type = "income";
            for (int i = 0; i < AllCategorie.size(); i++) {
                String val = AllCategorie.get(i).getName().toString();
                String ttype = AllCategorie.get(i).getTransactionType().getName().toString();
                if (ttype.equals(type))
                    list.add(new String(val));
            }

        }


        ArrayAdapter arrayAdapter;
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }
    catch (Exception ex){
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

                    Spinner spinner = (Spinner)findViewById(R.id.spinner);
                    String value = spinner.getSelectedItem().toString();

                    c = Category.getCategory(value);
                    if(c == null){
                        c = Category.getCategory("opće");
                    }
                    //Toast.makeText(getApplicationContext(), c.getName(), Toast.LENGTH_SHORT).show();
                    String dateTxt = ((TextView) findViewById(R.id.txtDate)).getText().toString();
                    /*
                    Date date = Calendar.getInstance().getTime();

                    Toast.makeText(getApplicationContext(), dateTxt, Toast.LENGTH_SHORT).show();

                    DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                    DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String inputDateStr = dateTxt;
                    Date datet = inputFormat.parse(inputDateStr);
                    String outputDateStr = outputFormat.format(datet);
                    */
                    // TODO Saving to db
                   transaction = new Transaction(ttype, c, c.getName(), dateTxt, note, amount);
                   transaction.save();

                    Toast.makeText(getApplicationContext(), "Uspješno dodano.", Toast.LENGTH_SHORT).show();
                }
                catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG).show();
                }
                finally {
                    Intent myIntent = new Intent(this, MainActivity.class);
                    myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(myIntent);
                    this.finish();
                    return true;
                }

            case R.id.action_cancel:
                Intent myIntent = new Intent(this, MainActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
                this.finish();
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
