package com.foi.air.potrosko;

import android.app.Activity;
import android.os.Bundle;

import com.foi.air.potrosko.fragments.SettingsFragment;

/**
 * Created by Ivan on 14.11.2015..
 */
public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

}
