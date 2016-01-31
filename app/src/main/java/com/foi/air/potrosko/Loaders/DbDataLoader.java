package com.foi.air.potrosko.Loaders;

import android.app.Activity;

import com.activeandroid.query.Select;
import com.foi.air.potrosko.core.DataLoader;
import com.foi.air.potrosko.db.Category;
import com.foi.air.potrosko.db.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrej on 31.1.2016..
 */
public class DbDataLoader extends DataLoader {
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

            dataLoaded(); // raise the event
        }
    }
}
