package com.foi.air.potrosko.core;

import android.app.Activity;

import com.foi.air.potrosko.db.Category;
import com.foi.air.potrosko.db.Transaction;

import java.util.ArrayList;

/**
 * Created by Andrej on 31.1.2016..
 */

/**
 * Klasa pomoću koje se učitavaju podaci
 */
public abstract class DataLoader {

    public ArrayList<Transaction> transactions;
    public ArrayList<Category> categories;
    OnDataLoadedListener dataLoadedListener;

    public void LoadData(Activity activity){
        if(dataLoadedListener == null)
            dataLoadedListener = (OnDataLoadedListener) activity;
    }

    /**
     * Kad podaci stignu iz baze poziva se metoda {@code dataLoaded()}
     * @return true or false
     */
    public boolean dataLoaded(){
        if(categories == null || transactions == null){
            return false;
        }
        else{
            dataLoadedListener.onDataLoaded(categories, transactions);
            return true;
        }
    }
}
