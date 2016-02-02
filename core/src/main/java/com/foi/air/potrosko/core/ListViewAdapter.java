package com.foi.air.potrosko.core;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.foi.air.potrosko.db.Transaction;

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
    //ListModel tempValues=null;
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
            //tempValues=null;
            //tempValues = ( ListModel ) data.get( position );

            tempValuesT=null;
            tempValuesT = (Transaction) data.get(position);

            // Set Model values in Holder elements
            holder.textCategory.setText(tempValuesT.getCategoryName());
            holder.textNote.setText(tempValuesT.getNote());
            holder.textAmount.setText(tempValuesT.getAmountString());
            holder.textDate.setText(tempValuesT.getDate());

            //TODO srediti dohvacanje ikona
            /*
            try{
                String mDrawableName = tempValuesT.getCategory().getCategoryImg();
                Toast.makeText(activity.getApplicationContext(), mDrawableName, Toast.LENGTH_SHORT).show();
                int resID = res.getIdentifier(mDrawableName , "drawable", activity.getPackageName());
                holder.image.setImageResource(resID);
            }catch (Exception ex){
                Toast.makeText(activity.getApplicationContext(), tempValuesT.getCategory().getCategoryImg(), Toast.LENGTH_SHORT).show();
                ex.printStackTrace();
            }
            */

            //String tempImg = tempValuesT.getCategory().getCategoryImg();
            //holder.image.setImageDrawable(activity.getResources().getDrawable(R.drawable.car));

            /*
            holder.image.setImageResource(
                    res.getIdentifier(
                            "com.foi.air.potrosko.app:drawable/"+image,null,null));
            */
            //Set Item Click Listner for LayoutInflater for each row

           vi.setOnClickListener(new OnItemClickListener( tempValuesT, activity));
        }
        return vi;
    }




}
