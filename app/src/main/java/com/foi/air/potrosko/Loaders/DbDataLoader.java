package com.foi.air.potrosko.loaders;

import android.app.Activity;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.foi.air.potrosko.R;
import com.foi.air.potrosko.core.DataLoader;
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
            Toast.makeText(activity, "Podaci se loadaju", Toast.LENGTH_SHORT).show();
            categories = (ArrayList<Category>) categoriesFromDb;
            transactions = (ArrayList<Transaction>) transactionsFromDb;

            dataLoaded();
        }
    }
}
