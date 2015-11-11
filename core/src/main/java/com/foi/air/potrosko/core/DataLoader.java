package com.foi.air.potrosko.core;

import android.app.Activity;

import com.foi.air.potrosko.db.Category;
import com.foi.air.potrosko.db.Transaction;

import java.util.ArrayList;

/**
 * Created by Ivan on 10.11.2015..
 */
public abstract class DataLoader {

    public ArrayList<Transaction> transactions;
    public ArrayList<Category> categories;
    public abstract void LoadData(Activity activity);

}
