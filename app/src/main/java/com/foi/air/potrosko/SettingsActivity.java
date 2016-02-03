package com.foi.air.potrosko;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.foi.air.potrosko.fragments.SettingsFragment;

/**Klasa koja u svom grafičkom sučelju
 * učitava fragment od postavki aplikacije
 */
public class SettingsActivity extends Activity {

    /** Metoda koja se koja za glavni sadržaj ove aktivnosti
     * učitava grafičko sučelje settings fragmenta iz datoteke
     * preferences.xml
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
    }

    /** Metoda koja na pritisak gumba back
     * prekida ovu aktivnost.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

}
