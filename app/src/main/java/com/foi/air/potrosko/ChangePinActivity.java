package com.foi.air.potrosko;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.foi.air.potrosko.core.SetupEvenlyDistributedToolbar;

/**
 * Created by Andrej on 15.11.2015..
 */
public class ChangePinActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_new_pin);

        // Toolbar
        //mToolbar = (Toolbar) findViewById(R.id.toolbar);

        //SetupEvenlyDistributedToolbar.setupEvenlyDistributedToolbar(getWindowManager().getDefaultDisplay(), mToolbar, R.menu.menu_new_transaction); // Calling new method for distributing icons
        //setSupportActionBar(mToolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.change_pin_actions, menu);

        return true;
    }


}
