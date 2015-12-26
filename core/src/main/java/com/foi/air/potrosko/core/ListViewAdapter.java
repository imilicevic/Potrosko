package com.foi.air.potrosko.core;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Marko Plaftarić on 02-Dec-15.
 */
public class ListViewAdapter extends BaseAdapter {


    // Declare Used Variables
    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater=null;
    public Resources res;
    ListModel tempValues=null;
    int i=0;

    //CustomAdapter Constructor
    public ListViewAdapter(Activity a, ArrayList d,Resources resLocal) {

        // Take passed values
        activity = a;
        data=d;
        res = resLocal;

        //Layout inflator to call external xml layout ()
        inflater = ( LayoutInflater )activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    //What is the size of Passed Arraylist Size
    public int getCount() {

        if(data.size()<=0)
            return 1;
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    // Create a holder Class to contain inflated xml file elements
    public static class ViewHolder{

        public TextView textCategory;
        public TextView textNote;
        public TextView textDate;
        public TextView textAmount;
        public ImageView image;

    }

    //Depends upon data size called for each row , Create each ListView row
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if(convertView==null){

            //Inflate tabitem.xml file for each row ( Defined below )
            vi = inflater.inflate(R.layout.tab_item, null);

            //View Holder Object to contain tabitem.xml file elements
            holder = new ViewHolder();
            holder.textCategory = (TextView) vi.findViewById(R.id.text_category);
            holder.textNote =(TextView)vi.findViewById(R.id.text_note);
            holder.textAmount =(TextView)vi.findViewById(R.id.text_amount);
            holder.textDate =(TextView)vi.findViewById(R.id.text_date);
            //holder.image=(ImageView)vi.findViewById(R.id.imageView);

            // Set holder with LayoutInflater
            vi.setTag( holder );
        }
        else
            holder=(ViewHolder)vi.getTag();

        if(data.size()<=0)
        {
            holder.textCategory.setText("No Data");
        }
        else
        {
            // Get each Model object from Arraylist
            tempValues=null;
            tempValues = ( ListModel ) data.get( position );

            // Set Model values in Holder elements
            holder.textCategory.setText(tempValues.getCategory());
            holder.textNote.setText(tempValues.getNote());
            holder.textAmount.setText(tempValues.getAmountString());
            holder.textDate.setText(tempValues.getDate());
            //holder.image.setImageDrawable(activity.getResources().getDrawable(R.drawable.car));
            //TODO srediti dohvacanje ikona
/*
            holder.image.setImageResource(
                    res.getIdentifier(
                            "com.foi.air.potrosko.app:drawable/"+tempValues.getImage()
                            ,null,null));

            //Set Item Click Listner for LayoutInflater for each row

 */           vi.setOnClickListener(new OnItemClickListener( tempValues, activity));
        }
        return vi;
    }





}