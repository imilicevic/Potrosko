package com.foi.air.potrosko.transactions;

import android.support.v7.internal.view.menu.ActionMenuItemView;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;

/**
 * This method will take however many items you have in your
 * menu/menu_new_transaction.xml and distribute them across your devices screen
 * evenly using a Toolbar. Implemented by plf.
 * source: https://stackoverflow.com/questions/26489079/evenly-spaced-menu-items-on-toolbar
 */
public class SetupEvenlyDistributedToolbar {

    public static void setupEvenlyDistributedToolbar(Display display, Toolbar mToolbar, int menu){

        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        mToolbar.inflateMenu(menu);

        /**
         * Add 10 spacing on either side of the toolbar
          */
        mToolbar.setContentInsetsAbsolute(10, 10);

        /**
         * Get the ChildCount of your Toolbar, this should only be 1
          */
        int childCount = mToolbar.getChildCount();
        /**
         * Get the Screen Width in pixels
         */
        int screenWidth = metrics.widthPixels;

        /**
         * Create the Toolbar Params based on the screenWidth
          */
        Toolbar.LayoutParams toolbarParams = new Toolbar.LayoutParams(screenWidth, android.widget.Toolbar.LayoutParams.WRAP_CONTENT);

        /**
         *  Loop through the child Items
         *  Get the item at the current index
         *   Get the child count of this view group, and compute the item widths based on this count & screen size
          */
        for(int i = 0; i < childCount; i++){

            View childView = mToolbar.getChildAt(i);
            //
            if(childView instanceof ViewGroup){
                childView.setLayoutParams(toolbarParams);
                int innerChildCount = ((ViewGroup) childView).getChildCount();
                int itemWidth  = (screenWidth / innerChildCount);
                /**
                 * Create layout params for the ActionMenuView
                  */
                ActionMenuView.LayoutParams params = new ActionMenuView.LayoutParams(itemWidth, android.widget.Toolbar.LayoutParams.WRAP_CONTENT);

                for(int j = 0; j < innerChildCount; j++){
                    View grandChild = ((ViewGroup) childView).getChildAt(j);
                    if(grandChild instanceof ActionMenuItemView){
                        grandChild.setLayoutParams(params);
                    }
                }
            }
        }
    }

}
