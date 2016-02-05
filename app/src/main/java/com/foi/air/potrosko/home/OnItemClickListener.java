package com.foi.air.potrosko.home;

import android.app.Activity;
import android.view.View;
import com.foi.air.potrosko.db.Transaction;

/**
 * Created by Marko Plaftarić on 09-Dec-15.
 */
// Called when Item click in ListView
public class OnItemClickListener implements View.OnClickListener {
    private int mPosition;
    private Transaction model;
    private Activity ac;

    public OnItemClickListener(Transaction model, Activity act) {
        this.model = model;
        this.ac = act;
    }

    @Override
    public void onClick(View arg0) {

           }
}


