package com.foi.air.potrosko;

import android.app.Activity;
import android.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.foi.air.potrosko.core.NavigationItem;
import com.foi.air.potrosko.db.Category;
import com.foi.air.potrosko.db.Transaction;

import java.util.ArrayList;

/**
 * Created by Andrej on 31.1.2016..
 */
public class NavigationManager {

    public ArrayList<NavigationItem> navigationItems;
    private static NavigationManager instance;
    private Activity mHandlerActivity;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private NavigationItem activeItem;
    private ArrayList<Category> categories;
    private ArrayList<Transaction> transactions;

    public void setDependencies(Activity handlerActivity, DrawerLayout drawerLayout, NavigationView navigationView){
        this.mHandlerActivity = handlerActivity;
        this.mNavigationView = navigationView;
        this.mDrawerLayout = drawerLayout;

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                selectItem(menuItem);
                return true;
            }


        });
    }

    private void selectItem(MenuItem menuItem) {
        // uses the menu item to find the NavigationItem (interface implementator)
        NavigationItem clickedItem = navigationItems.get(menuItem.getItemId());

        FragmentManager fragmentManager = mHandlerActivity.getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.flContent, clickedItem.getFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("")
                .commit();

        clickedItem.loadData(categories, transactions); // load data, only when the module is about to be shown

        menuItem.setChecked(true);
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    // private constructor
    private NavigationManager(){
        navigationItems = new ArrayList<NavigationItem>();
    }

    public static NavigationManager getInstance(){
        if(instance == null)
            instance = new NavigationManager();
        return instance;
    }

    public ArrayList<String> getNavigationItemsAsStrings(){
        ArrayList<String> navigationItemStrings = new ArrayList<String>();
        for (NavigationItem item : navigationItems) {
            navigationItemStrings.add(item.getItemName());
        }
        return navigationItemStrings;
    }

    public void reloadItems() {
        for (NavigationItem item : this.navigationItems) {
            mNavigationView.getMenu().add(0, item.getPosition(), 0, item.getItemName());
        }
    }

    public void clearItems()
    {
        navigationItems.clear();
    }

    public void loadDefaultFragment() {
        activeItem =navigationItems.get(0);
        FragmentManager fragmentManager = mHandlerActivity.getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.flContent, activeItem.getFragment())
                .commit();
    }

    public void addItem(NavigationItem newItem){
        newItem.setPosition(navigationItems.size());
        navigationItems.add(newItem);
        mNavigationView.getMenu().add(0, newItem.getPosition(), 0, newItem.getItemName());
    }

    public void makeDataChange(ArrayList<Category> categories, ArrayList<Transaction> transactions){
        this.categories = categories;
        this.transactions = transactions;
        activeItem.loadData(categories, transactions);  // load data to initial object
    }

}
