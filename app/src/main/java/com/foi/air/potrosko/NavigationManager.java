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

/**
 * Klasa upravlja dinamičkim dodavanjem modula u Navigation Drawer.
 *
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

    /**
     * Dependencies prenijeti do {@code }NavigationManager()} metode
     * @param handlerActivity se koristi za pristup {@code FragmentManager()}
     * @param drawerLayout se koristi za punjenje s {@code }NavigationItem}
     * @param navigationView sadrži listu menu itema
     */
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

    /**
     * Klikom na item se radi transkacija.
     * Dohvaća id itema i klikom na određeni item se otvara njegov fragment i postavlja se u Back stack.
     * Kad se fragment otvori učitavaju se podaci(kategorije i transakcije).
     * @param menuItem
     */
    private void selectItem(MenuItem menuItem) {
        NavigationItem clickedItem = navigationItems.get(menuItem.getItemId());

        FragmentManager fragmentManager = mHandlerActivity.getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.flContent, clickedItem.getFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("")
                .commit();

        clickedItem.loadData(categories, transactions);

        menuItem.setChecked(true);
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }


    private NavigationManager(){
        navigationItems = new ArrayList<NavigationItem>();
    }

    public static NavigationManager getInstance(){
        if(instance == null)
            instance = new NavigationManager();
        return instance;
    }

    /**
     *
     * @return listu stringova od itema koji se nalaze u Navigation Draweru
     */
    public ArrayList<String> getNavigationItemsAsStrings(){
        ArrayList<String> navigationItemStrings = new ArrayList<String>();
        for (NavigationItem item : navigationItems) {
            navigationItemStrings.add(item.getItemName());
        }
        return navigationItemStrings;
    }

    /**
     * Metoda koja ponovo učitava iteme kad su već jednom učitani.
     * Omogućuje da se ne dodaju svaki put isti itemi.
     */
    public void reloadItems() {
        for (NavigationItem item : this.navigationItems) {
            mNavigationView.getMenu().add(0, item.getPosition(), 0, item.getItemName());
        }
    }

    /**
     * Metoda za učitavanje zadanog fragmenta.
     * Učitava se kod samog pokretanja aplikacije.
     * U našem slučaju je to 0. ti fragment, HomeScreenFragment.
     */
    public void loadDefaultFragment() {
        activeItem =navigationItems.get(0);
        FragmentManager fragmentManager = mHandlerActivity.getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.flContent, activeItem.getFragment())
                .commit();

    }

    /**
     * Metoda za dodavanje novih itema u Navigation Drawer.
     * @param newItem novi item
     */
    public void addItem(NavigationItem newItem){
        newItem.setPosition(navigationItems.size());
        navigationItems.add(newItem);
        mNavigationView.getMenu().add(0, newItem.getPosition(), 0, newItem.getItemName());
    }

    /**
     * Učitavanje podataka u aktivnom fragmentu.
     * @param categories kategorije
     * @param transactions transakcije
     */
    public void makeDataChange(ArrayList<Category> categories, ArrayList<Transaction> transactions){
        this.categories = categories;
        this.transactions = transactions;
        activeItem.loadData(categories, transactions);
    }

}
