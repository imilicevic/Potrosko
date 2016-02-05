package com.foi.air.potrosko.home;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.foi.air.potrosko.db.Transaction;
import java.util.ArrayList;


/**
 * Created by Marko Plaftarić on 02-Dec-15.
 */

public class ListViewAdapter extends BaseAdapter {

    // Declare Used Variables
    private Activity activity;
    private ArrayList data;
    private LayoutInflater inflater;
    public Resources res;
    Transaction tempValuesT=null;
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
            vi = inflater.inflate(com.foi.air.potrosko.core.R.layout.tab_item, null);

            //View Holder Object to contain tabitem.xml file elements
            holder = new ViewHolder();
            holder.textCategory = (TextView) vi.findViewById(com.foi.air.potrosko.core.R.id.text_category);
            holder.textNote =(TextView)vi.findViewById(com.foi.air.potrosko.core.R.id.text_note);
            holder.textAmount =(TextView)vi.findViewById(com.foi.air.potrosko.core.R.id.text_amount);
            holder.textDate =(TextView)vi.findViewById(com.foi.air.potrosko.core.R.id.text_date);
            holder.image=(ImageView)vi.findViewById(com.foi.air.potrosko.core.R.id.imageView2);

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
            //tempValues=null;
            //tempValues = ( ListModel ) data.get( position );

            tempValuesT=null;
            tempValuesT = (Transaction) data.get(position);

            // Set Model values in Holder elements
            holder.textCategory.setText(tempValuesT.getCategoryName());
            holder.textNote.setText(tempValuesT.getNote());
            holder.textAmount.setText(tempValuesT.getAmountString());
            holder.textDate.setText(tempValuesT.getDate());


            if (tempValuesT.getCategoryName().equals("General")) {
                holder.image.setImageDrawable(ResourcesCompat.getDrawable(activity.getResources(), com.foi.air.potrosko.core.R.drawable.barbershop, null));
            }
            else if (tempValuesT.getCategoryName().equals("Food")){
                holder.image.setImageDrawable(ResourcesCompat.getDrawable(activity.getResources(), com.foi.air.potrosko.core.R.drawable.food, null));
            }
            else if (tempValuesT.getCategoryName().equals("Car")){
                holder.image.setImageDrawable(ResourcesCompat.getDrawable(activity.getResources(), com.foi.air.potrosko.core.R.drawable.car, null));
            }
            else if (tempValuesT.getCategoryName().equals("Travel")) {
                holder.image.setImageDrawable(ResourcesCompat.getDrawable(activity.getResources(), com.foi.air.potrosko.core.R.drawable.party, null));
            }
            else if (tempValuesT.getCategoryName().equals("House")) {
                holder.image.setImageDrawable(ResourcesCompat.getDrawable(activity.getResources(), com.foi.air.potrosko.core.R.drawable.ingredients, null));
            }
            else if (tempValuesT.getCategoryName().equals("Shopping")) {
                holder.image.setImageDrawable(ResourcesCompat.getDrawable(activity.getResources(), com.foi.air.potrosko.core.R.drawable.clothes, null));
            }
            else if (tempValuesT.getCategoryName().equals("Transport")) {
                holder.image.setImageDrawable(ResourcesCompat.getDrawable(activity.getResources(), com.foi.air.potrosko.core.R.drawable.car, null));
            }
            else
                holder.image.setImageDrawable(ResourcesCompat.getDrawable(activity.getResources(), com.foi.air.potrosko.core.R.drawable.mobile, null));


            //TODO srediti ostatak ikona

            vi.setOnClickListener(new OnItemClickListener(tempValuesT, activity));
        }
        return vi;
    }




}
