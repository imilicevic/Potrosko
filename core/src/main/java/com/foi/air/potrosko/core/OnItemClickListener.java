package com.foi.air.potrosko.core;

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

    OnItemClickListener(int position){
     mPosition = position;
    }

    @Override
    public void onClick(View arg0) {

        // SHOW ALERT
        //Toast.makeText(ac, "" + model.getCategory() + "  Amount:" + model.getAmountString(), Toast.LENGTH_LONG).show();


           }
}


