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

        List<Category> storesFromDb = null ;
        List<Transaction> discountsFromDb = null;

        boolean databaseQuerySuccessful = false;

        try{
            storesFromDb = new Select().all().from(Category.class).execute();
            discountsFromDb = new Select().all().from(Transaction.class).execute();

            databaseQuerySuccessful = true;
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        if(databaseQuerySuccessful == true && storesFromDb.size() > 0 ){

            categories = (ArrayList<Category>) storesFromDb;
            transactions = (ArrayList<Transaction>) discountsFromDb;

            dataLoaded(); // raise the event
        }
    }
}
