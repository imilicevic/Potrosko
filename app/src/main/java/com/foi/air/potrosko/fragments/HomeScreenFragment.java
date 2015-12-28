package com.foi.air.potrosko.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.foi.air.potrosko.R;
import com.foi.air.potrosko.core.ListModel;
import com.foi.air.potrosko.core.ListViewAdapter;
import com.foi.air.potrosko.db.Transaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeScreenFragment extends Fragment {

    ListView list;
    ListViewAdapter customAdapter;
    public ArrayList<ListModel> CustomListViewValuesArr = new ArrayList<ListModel>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home_screen, null);
        list = (ListView)v.findViewById(R.id.listViewHome);

        //TODO dohvatiti podatke iz activeAndroida umjesto setListData
        List<Transaction> transactions = Transaction.getAll();
        setListData();

        // get data from the table by the MyListAdapter
        customAdapter = new ListViewAdapter(this.getActivity(),CustomListViewValuesArr, getResources());
        list.setAdapter(customAdapter);

        return v;
    }


    // Function to set data in ArrayList
    public void setListData()
    {

        List<Transaction> transactions = Transaction.getAll();
        for (int i = 0; i < transactions.size(); i++) {

            final ListModel myList = new ListModel();
            Transaction t = transactions.get(i);

            // Firstly take data in model object
            try {
                myList.setCategory(t.getCategory().getName());
            }catch (Exception ex){
                myList.setCategory("Opće");
            }
            myList.setAmount(t.getAmount());

            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
            String date = "";
            try {
                date = sdf.format(t.getDate());
            }catch (Exception ex){
                date = sdf.format(Calendar.getInstance().getTime());
            }finally {
                myList.setDate(date);
            }
            myList.setNote(t.getNote());
            //myList.setImage("Slika" + i);

            // Take Model Object in ArrayList
            CustomListViewValuesArr.add(myList);
        }

    }

    //  This function used by adapter
    public void onItemClick(int mPosition)
    {
        ListModel tempValues = ( ListModel ) CustomListViewValuesArr.get(mPosition);
        //TODO ovdje pozvati activity za editiranje unosa
        // SHOW ALERT
        
        Toast.makeText(getActivity(),""+tempValues.getCategory()+"Amount:"+tempValues.getAmountString(),Toast.LENGTH_SHORT).show();
    }




}
