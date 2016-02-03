package com.foi.air.potrosko.core;

import android.app.Fragment;

import com.foi.air.potrosko.db.Category;
import com.foi.air.potrosko.db.Transaction;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Andrej on 31.1.2016..
 */

/**
 * Interface s metodama koje se implementiraju u fragmentima
 */
public interface NavigationItem {
    public String getItemName();
    public int getPosition();
    public void setPosition(int position);
    public Fragment getFragment();
    public void loadData(ArrayList<Category> categories, ArrayList<Transaction> transactions);

}
