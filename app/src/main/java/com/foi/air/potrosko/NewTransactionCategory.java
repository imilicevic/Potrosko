package com.foi.air.potrosko;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.internal.view.menu.ActionMenuItemView;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

public class NewTransactionCategory extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transaction_category);

        //toolbar = (Toolbar) findViewById(R.id.toolbar); // Attaching the layout to the toolbar object
        setupEvenlyDistributedToolbar(); // Calling new method for distributing icons
        setSupportActionBar(mToolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        EditText edittext = (EditText) findViewById(R.id.DatePicker);
        EditTextDatePicker datepicker = new EditTextDatePicker(this, edittext);
        datepicker.setCurrentDate();
        /*
        SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
        edittext.setText( sdf.format( new Date() ));  */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_transaction, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.action_accept:
                Intent myIntent = new Intent(this, MainActivity.class);
                startActivity(myIntent);
                return true;
            case R.id.action_cancel:
                this.finish();
                /*
                Intent myIntent2 = new Intent(this, NewTransaction.class);
                startActivity(myIntent2);
                */
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    public void addActivity(View view) {
    // Do something in response to button
        Intent intent = new Intent(this, AddCategory.class);
        // TODO startActivityForResult
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    //TODO implementirati setupEvenlyDistributedToolbar metodu modularno tako da NewTransaction, NewTransactionCategory i AddCategory imaju pristup
    /**
     * This method will take however many items you have in your
     * menu/menu_new_transaction.xml and distribute them across your devices screen
     * evenly using a Toolbar. Implemented by plf.
     * source: https://stackoverflow.com/questions/26489079/evenly-spaced-menu-items-on-toolbar
     */
    public void setupEvenlyDistributedToolbar(){
        // Use Display metrics to get Screen Dimensions
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        // Toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        // Inflate your menu
        mToolbar.inflateMenu(R.menu.menu_new_transaction);

        // Add 10 spacing on either side of the toolbar
        mToolbar.setContentInsetsAbsolute(10, 10);

        // Get the ChildCount of your Toolbar, this should only be 1
        int childCount = mToolbar.getChildCount();
        // Get the Screen Width in pixels
        int screenWidth = metrics.widthPixels;

        // Create the Toolbar Params based on the screenWidth
        Toolbar.LayoutParams toolbarParams = new Toolbar.LayoutParams(screenWidth, android.widget.Toolbar.LayoutParams.WRAP_CONTENT);

        // Loop through the child Items
        for(int i = 0; i < childCount; i++){
            // Get the item at the current index
            View childView = mToolbar.getChildAt(i);
            // If its a ViewGroup
            if(childView instanceof ViewGroup){
                // Set its layout params
                childView.setLayoutParams(toolbarParams);
                // Get the child count of this view group, and compute the item widths based on this count & screen size
                int innerChildCount = ((ViewGroup) childView).getChildCount();
                int itemWidth  = (screenWidth / innerChildCount);
                // Create layout params for the ActionMenuView
                ActionMenuView.LayoutParams params = new ActionMenuView.LayoutParams(itemWidth, android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                // Loop through the children
                for(int j = 0; j < innerChildCount; j++){
                    View grandChild = ((ViewGroup) childView).getChildAt(j);
                    if(grandChild instanceof ActionMenuItemView){
                        // set the layout parameters on each View
                        grandChild.setLayoutParams(params);
                    }
                }
            }
        }
    }


}
