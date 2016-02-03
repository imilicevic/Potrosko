package com.foi.air.potrosko;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import com.activeandroid.ActiveAndroid;
import com.foi.air.potrosko.core.OnDataLoadedListener;
import com.foi.air.potrosko.db.Category;
import com.foi.air.potrosko.db.Transaction;
import com.foi.air.potrosko.fragments.ChartScreenFragment;
import com.foi.air.potrosko.fragments.HomeScreenFragment;
import com.foi.air.potrosko.fragments.SettingsFragment;
import com.foi.air.potrosko.transactions.TransactionActivity;
import java.util.ArrayList;

/**
 *Implementacija drawera
 */
public class MainActivity extends AppCompatActivity implements android.app.FragmentManager.OnBackStackChangedListener, OnDataLoadedListener{

    private DrawerLayout dlDrawer;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;
    private android.app.FragmentManager mFm;
    NavigationManager nm;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActiveAndroid.initialize(this);

        /**
         * Floating button
         */
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), TransactionActivity.class));
            }
        });

        /**
         * Seting toolbar and drawer
         */
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();
        dlDrawer.setDrawerListener(drawerToggle);

        nm = NavigationManager.getInstance();
        nm.setDependencies(this, dlDrawer, (NavigationView) findViewById(R.id.nvView));

        if(savedInstanceState == null){  // running this for the first time
            mFm = getFragmentManager();
            mFm.addOnBackStackChangedListener(this);
            toolbar.setNavigationOnClickListener(navigationClick);

            /**
             * Adding new modules to Navigation Drawer
             */
            nm.addItem(new HomeScreenFragment());
            nm.addItem(new ChartScreenFragment());
            nm.addItem(new SettingsFragment());
            nm.loadDefaultFragment();

        } else {  // running to reuse existing fragments
            nm.reloadItems();
        }
    }

    /**
     * Metoda za postavljanje hamburgera
     * @return hamurger ikona u toolbaru
     */
    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, dlDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    /**
     * Metoda za upravljanje kod korištenja Back buttona.
     * Provjerava ima li fragmenata u stacku.
     * Ako ima vraća na njih, ako nema izlazi iz aplikacije.
     */
    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() != 0){
            // there is something on the stack, I'm in the fragment
            if(dlDrawer.isDrawerOpen(GravityCompat.START)){
                dlDrawer.closeDrawer(GravityCompat.START);
            }
            else{
                getFragmentManager().popBackStack();
            }
        } else {
            // I'm on the landing page, close the drawer or exit
            if(dlDrawer.isDrawerOpen(GravityCompat.START)){
                dlDrawer.closeDrawer(GravityCompat.START);
            }
            else{
                super.onBackPressed();
            }
        }
    }

    /**
     * Sinkronizira stanje hamburger ikone te ga prikazuje.
     * @param savedInstanceState - kad se aplikacija pokrene prvi put bundle je null.
     *                           Ako se promijeni orijentacija šalje se {@code onPostCretae()}
     *                           metodi kako se ne bi izgubili podaci jer se tad aplikacija ponovo pokreće.
     */
   @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    /**
     * Pazi na Drawer toggle button ako se promijeni orijentacija ekrana.
     * @param newConfig nova konfiguracija, npr. vodoravni prikaz
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    /**
     * Upravlja prikazom Hamburger ikone i strelice(arrow icon).
     * Kad je back stack prazan prikazuje Hamburger ikonu.
     * Kad nije prazan prikazuje arrow icon.
     * Klikom na arrow icon se vraća na fragment u stacku.
     */
    @Override
    public void onBackStackChanged() {
        drawerToggle.setDrawerIndicatorEnabled(mFm.getBackStackEntryCount() == 0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(mFm.getBackStackEntryCount() > 0);
        drawerToggle.syncState();
    }

    @Override
    public void onDataLoaded(ArrayList<Category> categories, ArrayList<Transaction> transactions) {
        nm.makeDataChange(categories, transactions);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    /*
    EVENT HANDLERS
    */
    /**
     * Listener koji klikom na ikonicu zove prikladno ponašanje.
     * Ako se nalazi na landing pageu prikazuje navigation drawer, inače press back.
     */
   View.OnClickListener navigationClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(getFragmentManager().getBackStackEntryCount() == 0) {
                dlDrawer.openDrawer(GravityCompat.START);
            }
            else{
                onBackPressed();
            }
        }
    };


}
