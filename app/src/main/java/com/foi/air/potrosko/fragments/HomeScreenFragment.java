package com.foi.air.potrosko.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.activeandroid.query.Delete;
import com.foi.air.potrosko.Loaders.DbDataLoader;
import com.foi.air.potrosko.R;
import com.foi.air.potrosko.core.DataLoader;
import com.foi.air.potrosko.core.NavigationItem;
import com.foi.air.potrosko.db.Category;
import com.foi.air.potrosko.db.Transaction;
import com.foi.air.potrosko.core.ListViewAdapter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeScreenFragment extends Fragment implements NavigationItem{

    ListView list;
    ListViewAdapter customAdapter;
    public ArrayList<Transaction> CustomListViewValuesArr = new ArrayList<Transaction>();
    private int position;
    private String name = "Overview";
    private ArrayList<Category> categories;
    private ArrayList<Transaction> transactions;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home_screen, null);
        list = (ListView)v.findViewById(R.id.listViewHome);

        // dohvatiti podatke iz activeAndroida
        final List<Transaction> transactions = Transaction.getAll();
        setListData();

        // get data from the table by the MyListAdapter
        customAdapter = new ListViewAdapter(this.getActivity(),CustomListViewValuesArr, getResources());
        list.setAdapter(customAdapter);

        // long click listener implementation
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent,final View v, final int position, final long id) {
                try{
                    //transaction dialogs
                    AlertDialog.Builder dialogBuilderMain = new AlertDialog.Builder(getActivity());
                    dialogBuilderMain.setMessage(getActivity().getResources().getString(R.string.transaction_options))
                            .setPositiveButton(getActivity().getResources().getString(R.string.delete), new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which){
                                        case DialogInterface.BUTTON_POSITIVE:

                                            //delete transaction
                                            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                                            dialogBuilder.setMessage(getActivity().getResources().getString(R.string.delete_transaction))
                                                    .setPositiveButton(getActivity().getResources().getString(R.string.yes), new DialogInterface.OnClickListener(){
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            switch (which){
                                                                case DialogInterface.BUTTON_POSITIVE:

                                                                    String textCategoty = ((TextView) v.findViewById(R.id.text_category)).getText().toString();
                                                                    //Toast.makeText(getActivity(), textCategoty, Toast.LENGTH_SHORT).show();
                                                                    //list.getSelectedItem();

                                                                    //Toast.makeText(getActivity(), ((list.getItemAtPosition(position).toString())), Toast.LENGTH_SHORT).show();

                                                                    String textAmount = ((TextView) v.findViewById(R.id.text_amount)).getText().toString();
                                                                    Float ftxt = Float.parseFloat(textAmount);
                                                                    Integer itxt = Math.round(ftxt);
                                                                    //Toast.makeText(getActivity(), String.valueOf(itxt), Toast.LENGTH_SHORT).show();

                                                                    String textNote = ((TextView) v.findViewById(R.id.text_note)).getText().toString();
                                                                    //Toast.makeText(getActivity(), textNote, Toast.LENGTH_SHORT).show();

                                                                    String textDate = ((TextView) v.findViewById(R.id.text_date)).getText().toString();
                                                                    //Toast.makeText(getActivity(), textDate, Toast.LENGTH_SHORT).show();

                                                                    /*
                                                                    Integer selectedFromList = (Integer)(list.getItemAtPosition(position));
                                                                    String s = String.valueOf(selectedFromList);
                                                                    Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show(); //ispisuje poziciju u listviewu

                                                                    Transaction tr = Transaction.getTr(textCategoty, textAmount, textNote, textDate);
                                                                    Integer transactionID = Integer.parseInt(tr.getId().toString());
                                                                    Toast.makeText(getActivity(), transactionID.toString(), Toast.LENGTH_LONG).show(); // ne radi
                                                                    */

                                                                    Toast.makeText(getActivity(), String.valueOf(id), Toast.LENGTH_LONG).show();
                                                                    //TODO Doraditi logiku za brisanje

                                                                    //new Delete().from(Transaction.class).where("  Id  = ?", list.getCount()).execute();

                                                                    new Delete().from(Transaction.class).where("  amount  = ?", itxt).and(" note = ?", textNote).execute();
                                                                    CustomListViewValuesArr.remove(position);
                                                                    customAdapter.notifyDataSetChanged();

                                                                    Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.delete_successful), Toast.LENGTH_LONG).show();

                                                                    break;
                                                            }
                                                        }
                                                    })
                                                    .setNegativeButton(getActivity().getResources().getString(R.string.no),null)
                                                    .show();
                                            break;
                                    }
                                }
                            })
                            .setNegativeButton(getActivity().getResources().getString(R.string.edit), new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // edit transactions

                                    // TODO Logika za izmjenu podataka se poziva ovdje

                                    Toast.makeText(getActivity(), "Izmjena podataka", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .show();
                    // return true to say "event handled"
                    return true;

                }catch (Exception ex){
                    Toast.makeText(getActivity().getBaseContext(), ex.toString(), Toast.LENGTH_LONG).show();
                }finally {
                    //Toast.makeText(getActivity().getBaseContext(), "Long click je uspješan", Toast.LENGTH_SHORT).show();
                }



                return true;
            }
        });

        return v;
    }

    @Override
    public String getItemName() {
        return name;
    }


    @Override
    public int getPosition() {
        return position;
    }


    @Override
    public void setPosition(int position) {
        this.position = position;
    }


    @Override
    public android.app.Fragment getFragment() {
        return this;
    }

    @Override
    public void loadData(ArrayList<Category> categories, ArrayList<Transaction> transactions) {

    }



    // Function to set data in ArrayList
    public void setListData()
    {

        List<Transaction> transactions = Transaction.getAll();
        List<Category> categories = Category.getAll();
        for (int i = 0; i < transactions.size(); i++) {

            final Transaction myList = new Transaction();
            Transaction t = transactions.get(i);

            // TODO srediti imena kategorija
            Category c = categories.get(1);

            // Firstly take data in model object
            try {
                myList.setCategory(t.getCategory());
            }catch (Exception ex){
                myList.setCategory(c);
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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DataLoader dl = null;
        dl = new DbDataLoader();
        dl.LoadData(getActivity());
    }




    //  This function used by adapter
    public void onItemClick(int mPosition)
    {
        Transaction tempValues = (Transaction) CustomListViewValuesArr.get(mPosition);
        //TODO ovdje pozvati activity za editiranje unosa
        // SHOW ALERT
        
        Toast.makeText(getActivity(),""+tempValues.getCategory()+"Amount:"+tempValues.getAmountString(),Toast.LENGTH_SHORT).show();
    }




}
