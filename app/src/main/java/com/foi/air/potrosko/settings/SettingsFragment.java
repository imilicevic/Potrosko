package com.foi.air.potrosko.settings;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;
import android.preference.Preference.OnPreferenceClickListener;

import android.view.MenuItem;
import android.view.View;

import com.foi.air.potrosko.R;
import com.foi.air.potrosko.core.NavigationItem;
import com.foi.air.potrosko.db.Category;
import com.foi.air.potrosko.db.Transaction;

import java.util.ArrayList;

/**
 * Created by Ivan on 14.11.2015..
 * Settings fragment sadrži prikaz postavki u aplikaciji
 */
public class SettingsFragment extends PreferenceFragment implements NavigationItem {
    private Class fragmentClass;
    private String name = "Settings";
    private int position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }


    @Override
    public String getItemName() {
        return name;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public android.app.Fragment getFragment() {
        return this;
    }

    @Override
    public void loadData(ArrayList<Category> categories, ArrayList<Transaction> transactions) {}


}

