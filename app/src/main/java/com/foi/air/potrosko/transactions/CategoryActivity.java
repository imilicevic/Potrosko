package com.foi.air.potrosko.transactions;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.util.SQLiteUtils;
import com.foi.air.potrosko.MainActivity;
import com.foi.air.potrosko.R;
import com.foi.air.potrosko.db.Category;
import com.foi.air.potrosko.db.Transaction;
import com.foi.air.potrosko.db.TransactionType;

import java.util.ArrayList;
import java.util.List;

/**
 * Odabir željene kategorije, upis datuma i bilješke.
 * Spremanje transakcije u bazu.
 */
public class CategoryActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView txtAmount;
    private int tempId = -1;
    private String cat;
    private String trueId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        SetupEvenlyDistributedToolbar.setupEvenlyDistributedToolbar(getWindowManager().getDefaultDisplay(), mToolbar, R.menu.menu_new_transaction); // Calling new method for distributing icons

        setSupportActionBar(mToolbar);
        txtAmount = (TextView) findViewById(R.id.txtAmount);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        EditText edittext = (EditText) findViewById(R.id.txtDate);
        EditTextDatePicker datepicker = new EditTextDatePicker(this, edittext);
        datepicker.setCurrentDate();


        /**
         * Getting data from previous activity (TransactionActivity)
         */
        List<TransactionType> transactionTypes = TransactionType.getAll();
        if (transactionTypes == null || transactionTypes.size() <= 0) {
            TransactionType expense = new TransactionType("expense", "amounts spent during time period");
            TransactionType income = new TransactionType("income", "amounts received during time period");

            expense.save();
            income.save();

            List<Category> categories = Category.getAll();
            //Toast.makeText(getApplicationContext(), categories.toString(), Toast.LENGTH_LONG).show();
            if (categories == null || categories.size() <= 0) {

                /**
                 *  expense categories
                 */
                Category general = new Category("General", "Općenite transakcije, nesvrstane.", expense, "barbershop");
                Category food = new Category("Food", "Izdavanja na hranu.", expense, "food");
                Category car = new Category("Car", "Sva izdavanja za automobil.", expense, "car");
                Category travel = new Category("Travel", "Sva izdavanja za putovanja.", expense, "party");
                Category home = new Category("House", "Izdavanja vezana uz kućanstvo.", expense, "ingredients");
                Category shopping = new Category("Shopping", "Izdavanja vezana uz shopping", expense, "clothes");
                Category transport = new Category("Transport", "Izdavanja za prijevoz.", expense, "car");
                Category other = new Category("Other", "Ostala nesvrstana plaćanja.", expense, "mobile");

                /**
                 * income categories
                 */
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

            }
        }
        int id = 0;
        Intent inte = getIntent();
        String str = inte.getStringExtra("id");
        trueId = str;
        try {
            id = Integer.parseInt(str);
            tempId = id;
        } catch (Exception ex) {
        }
        /**
         * Dohvaćanje podataka item-a kojeg se zeli izmjeniti
         */
        if (id > 0) {

            Intent myIntent = getIntent();
            if (myIntent.hasExtra("amount")) {
                TextView mText = (TextView) findViewById(R.id.txtAmount);
                mText.setText(myIntent.getStringExtra("amount"));
            }
            if (myIntent.hasExtra("category")) {
                cat = myIntent.getStringExtra("category");
                Spinner mSpin = (Spinner) findViewById(R.id.spinner);
                List<Category> AllCategorie;
                AllCategorie = (List<Category>) Category.getAll();
                for (int i = 0; i < AllCategorie.size(); i++) {
                    if(AllCategorie.get(i).getName().equals(myIntent.getStringExtra("category"))){

                        mSpin.setSelection(i, true);
                        //Log.e("KAT", i + " kategorija");
                    }
                }
            }
            if (myIntent.hasExtra("note")) {
                TextView mText = (TextView) findViewById(R.id.txtNote);
                mText.setText(myIntent.getStringExtra("note"));
            }
            if (myIntent.hasExtra("date")) {
                TextView mText = (TextView) findViewById(R.id.txtDate);
                mText.setText(myIntent.getStringExtra("date"));
            }

        } else {
            try {
                Intent intent = getIntent();
                String s = intent.getStringExtra("myAmount");
                Double amount = Double.parseDouble(s);

                TextView txtAmount = (TextView) findViewById(R.id.txtAmount);
                txtAmount.setText(amount.toString());
            } catch (Exception ex) {
            }
        }


        /**
         * Kreiranje spinnera i dodavanje item-a
         * Sortiranje kategorija unutar spinnera u odnosnu na prethodni odabir Income/Expense
         */
        Spinner spinner;

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayList<String> list = new ArrayList<String>();

        List<Category> AllCategorie;
        AllCategorie = (List<Category>) Category.getAll();

        String type = "";
        String value = txtAmount.getText().toString();

        try {
            Double amount = Double.parseDouble(value);

            if (amount < 0) {
                type = "expense";
                for (int i = 0; i < AllCategorie.size(); i++) {
                    String val = AllCategorie.get(i).getName();
                    String ttype = AllCategorie.get(i).getTransactionType().getName();
                    if (ttype.equals(type))
                        list.add(val);
                }
            } else {
                type = "income";
                for (int i = 0; i < AllCategorie.size(); i++) {
                    String val = AllCategorie.get(i).getName();
                    String ttype = AllCategorie.get(i).getTransactionType().getName();
                    if (ttype.equals(type))
                        list.add(val);
                }
            }

            ArrayAdapter arrayAdapter;
            arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(arrayAdapter);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_transaction, menu);
        return true;
    }

    /**
     * Handle action bar item clicks here. The action bar will
     * automatically handle clicks on the Home/Up button, so long
     * as you specify a parent activity in AndroidManifest.xml.
     *
     * @param item
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Transaction transaction = new Transaction();
        TransactionType ttype;
        Category c;

        switch (item.getItemId()) {
            case R.id.action_accept:

                    try {
                        if (tempId < 0)
                        {
                            Intent intent = getIntent();
                            String s = intent.getStringExtra("myAmount");
                            Double amount = Double.parseDouble(s);

                            //setting Transaction type

                            if (amount < 0) {
                                ttype = TransactionType.getType("expense");
                            } else {
                                ttype = TransactionType.getType("income");
                            }
                            String note = ((TextView) findViewById(R.id.txtNote)).getText().toString();

                            Spinner spinner = (Spinner) findViewById(R.id.spinner);
                            String value = spinner.getSelectedItem().toString();

                            c = Category.getCategory(value);
                            if (c == null) {
                                c = Category.getCategory("opće");
                            }
                            String dateTxt = ((TextView) findViewById(R.id.txtDate)).getText().toString();

                            transaction = new Transaction(ttype, c, c.getName(), dateTxt, note, amount);
                            transaction.save();

                            Toast.makeText(getApplicationContext(), "Uspješno dodano.", Toast.LENGTH_SHORT).show();
                        }
                        else{

                            String amountStr = ((TextView) findViewById(R.id.txtAmount)).getText().toString();
                            Spinner spinner = (Spinner) findViewById(R.id.spinner);
                            String catSpin = spinner.getSelectedItem().toString();
                            String dateStr = ((TextView) findViewById(R.id.txtDate)).getText().toString();
                            String noteStr = ((TextView) findViewById(R.id.txtNote)).getText().toString();
                            if(noteStr.isEmpty()){noteStr = " ";}
                            if(!isValidInput(amountStr)){
                                Toast.makeText(this, "Unesite broj sa max 7 znamenki molim!", Toast.LENGTH_LONG).show();
                                return false;
                            }

                            List<Transaction> t = SQLiteUtils.rawQuery(Transaction.class,
                                    "UPDATE Transactions SET amount = ?, date = ?, note = ?" +
                                            " WHERE Transactions.id = ?"
                                    , new String[]{amountStr, dateStr, noteStr, trueId});

                            //Activeandroid sintaxa neispravna
                            //new Update(Transaction.class).set(amountStr,noteStr,dateStr).where("id = ? AND date=?", tempId).execute();

                        }
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG).show();
                } finally {
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
        Intent intent = new Intent(this, AddCategoryActivity.class);
        intent.putExtra("myAmount", getIntent().getStringExtra("myAmount"));
        startActivity(intent);
    }

    // validacija unosa
    private boolean isValidInput(String input) {
        if (input.length() > 7 || input.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

}
