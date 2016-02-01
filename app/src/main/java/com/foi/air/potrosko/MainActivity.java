package com.foi.air.potrosko;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
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

public class MainActivity extends AppCompatActivity implements android.app.FragmentManager.OnBackStackChangedListener, OnDataLoadedListener {

    private DrawerLayout dlDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private Class fragmentClass;
    private ActionBarDrawerToggle drawerToggle;
    private Intent intent;
    private ImageButton floatButton;
    private android.app.FragmentManager mFm;
    NavigationManager nm;
    private HomeScreenFragment hsf;
    private SettingsFragment sf;
    // Alert Dialog Manager
    //AlertDialogManager alert = new AlertDialogManager();



    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActiveAndroid.initialize(this);

        //floating button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), TransactionActivity.class));
            }
        });

        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Find our drawer view
        dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // Find our drawer view
        //nvDrawer = (NavigationView) findViewById(R.id.nvView);
        // Setup drawer view
        //setupDrawerContent(nvDrawer);

        drawerToggle = setupDrawerToggle();
        // Tie DrawerLayout events to the ActionBarToggle
        dlDrawer.setDrawerListener(drawerToggle);

        mFm = getFragmentManager();
        mFm.addOnBackStackChangedListener(this);

        toolbar.setNavigationOnClickListener(navigationClick);

        nm = NavigationManager.getInstance();
        nm.setDependencies(this, dlDrawer, (NavigationView) findViewById(R.id.nvView));

        if(savedInstanceState == null){  // running this for the first time
            mFm = getFragmentManager();
            mFm.addOnBackStackChangedListener((android.app.FragmentManager.OnBackStackChangedListener) this);
            toolbar.setNavigationOnClickListener(navigationClick);

            // add the modules, only once, only here
            nm.addItem(new HomeScreenFragment());
            nm.addItem(new ChartScreenFragment());
            nm.addItem(new SettingsFragment());
            nm.loadDefaultFragment();

        } else {  // running to reuse existing fragments
            nm.reloadItems(); // do not add modules again, reuse existing ones
        }
        // Postavljam pocetni fragment kod startanja
        /*if(savedInstanceState == null) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            fragmentClass = HomeScreenFragment.class;
            Fragment fragmento = null;
            try {
                fragmento = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            transaction.replace(R.id.flContent, fragmento).commit();
        }*/

    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, dlDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    /*private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }*/

    /*public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the planet to show based on position
        Fragment fragment = null;

        switch(menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                fragmentClass = HomeScreenFragment.class;
                break;
            case R.id.nav_second_fragment:
                fragmentClass = ChartScreenFragment.class;
                break;
            case R.id.nav_third_fragment:
                startActivity(new Intent(getBaseContext(), SettingsActivity.class));
                break;
            case R.id.nav_fourth_fragment:
                startActivity(new Intent(getBaseContext(), TransactionActivity.class));
                break;
            default:
                fragmentClass = HomeScreenFragment.class;

        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item, update the title, and close the drawer
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        dlDrawer.closeDrawers();

    }*/

    // Make sure this is the method with just `Bundle` as the signature
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

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

    @Override
    public void onBackStackChanged() {
        drawerToggle.setDrawerIndicatorEnabled(mFm.getBackStackEntryCount() == 0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(mFm.getBackStackEntryCount() > 0);
        drawerToggle.syncState();
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

    @Override
    public void onDataLoaded(ArrayList<Category> categories, ArrayList<Transaction> transactions) {
        nm.makeDataChange(categories, transactions);
    }
}
