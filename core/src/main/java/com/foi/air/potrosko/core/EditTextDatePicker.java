package com.foi.air.potrosko.core;

import android.app.DatePickerDialog;
import android.content.Context;

import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import java.util.Calendar;


/**
 * Created by Marko Plaftarić on 11/29/2015. Class used in NewTransactionCategory for setting up the Date functionality
 */
public class EditTextDatePicker implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    EditText editText;
    private int day;
    private int month;
    private int year;
    private Context context;

    public EditTextDatePicker(Context context, EditText edit)
    {
        this.editText = edit;
        this.editText.setOnClickListener(this);
        this.context = context;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        this.year = year;
        month = monthOfYear;
        day = dayOfMonth;
        updateDisplay();
    }

    public void onClick(View v) {
        DatePickerDialog dialog =  new DatePickerDialog(context, this, year, month, day);
        dialog.show();
    }

    // setting default current date
    public void setCurrentDate(){
        final Calendar c = Calendar.getInstance();
        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH);
        year = c.get(Calendar.YEAR);
        updateDisplay();
    }

    // updates the date in the EditText
    private void updateDisplay() {

        editText.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append(day).append("/").append(month + 1).append("/").append(year).append(" "));
    }
}
