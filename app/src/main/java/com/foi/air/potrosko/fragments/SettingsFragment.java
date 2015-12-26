package com.foi.air.potrosko.fragments;

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

/**
 * Created by Ivan on 14.11.2015..
 */
public class SettingsFragment extends PreferenceFragment {
    private Class fragmentClass;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);


    }








    }

