package com.foi.air.potrosko.core;

import com.foi.air.potrosko.db.Category;
import com.foi.air.potrosko.db.Transaction;

import java.util.ArrayList;

/**
 * Created by Andrej on 31.1.2016..
 */
public interface OnDataLoadedListener {
    public void onDataLoaded(ArrayList<Category> categories, ArrayList<Transaction> transactions);
}
