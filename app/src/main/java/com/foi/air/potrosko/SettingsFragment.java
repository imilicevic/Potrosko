package com.foi.air.potrosko;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Ivan on 14.11.2015..
 */
public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);



    }
}
