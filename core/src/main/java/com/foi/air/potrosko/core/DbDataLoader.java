package com.foi.air.potrosko.core;

import android.app.Activity;

import com.activeandroid.query.Select;
import com.activeandroid.util.SQLiteUtils;
import com.foi.air.potrosko.db.Category;
import com.foi.air.potrosko.db.Transaction;

import java.util.ArrayList;
import java.util.List;


/**Klasa koja je zadužena za dohvaćanje podataka iz DB tablica
 * Category i Transaction te punjenje objekata liste istima
 */
public class DbDataLoader extends DataLoader {

    /** Metoda koja u liste iz baze podataka dohvaća sve retke
     * iz tablica Category i Transaction.
     * Ako je load podataka uspješan, prikazuje se toast poruka
     * te se loadani podaci nakon casta kopiraju u nove objekte categories
     * i transactions
     */
    @Override
    public void LoadData(Activity activity) {
        super.LoadData(activity); // setup the event listener (MainActivity)

        List<Category> categoriesFromDb = null ;
        List<Transaction> transactionsFromDb = null;

        boolean databaseQuerySuccessful = false;

        try{
            categoriesFromDb = new Select().all().from(Category.class).execute();
            transactionsFromDb = new Select().all().from(Transaction.class).execute();

            databaseQuerySuccessful = true;
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        if(databaseQuerySuccessful == true && categoriesFromDb.size() > 0 ){
            categories = (ArrayList<Category>) categoriesFromDb;
            transactions = (ArrayList<Transaction>) transactionsFromDb;

            dataLoaded();
        }
    }

    /**Metoda koja dohvaća podatke iz baze koje koriste Pie chartovi
     *
     * @param transactionType
     * @return lista transakcija
     */
    public List<Transaction> LoadChartData (String transactionType){

        List<Transaction> t = SQLiteUtils.rawQuery(Transaction.class,
                "Select * from Transactions JOIN TransactionType on TransactionType = TransactionType.id where TransactionType.name=?"
                , new String[]{transactionType});

        return t;
    }


    /**Za zadane parametre upit dohvaća id iz baze. Koristi se u HomeScreenFragmentu za Edit i Delete
     *
     * @return id
     */
    public List<Transaction> LoadMyId (String strNote, String strAmount, String strDate, String strCategory){

        List<Transaction> t = SQLiteUtils.rawQuery(Transaction.class,
                "SELECT Transactions.id from Transactions join Categories on Transactions.Category = Categories.id " +
                        "where note = ? and amount = ? and date = ? and Categories.name = ?"
                , new String[]{strNote,strAmount,strDate,strCategory});

        return  t;
    }

}
