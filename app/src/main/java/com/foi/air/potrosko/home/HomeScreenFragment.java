package com.foi.air.potrosko.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.activeandroid.util.SQLiteUtils;
import com.foi.air.potrosko.core.DbDataLoader;
import com.foi.air.potrosko.R;
import com.foi.air.potrosko.core.DataLoader;
import com.foi.air.potrosko.core.NavigationItem;
import com.foi.air.potrosko.db.Category;
import com.foi.air.potrosko.db.Transaction;
import com.foi.air.potrosko.transactions.CategoryActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class HomeScreenFragment extends Fragment implements NavigationItem{

    private ListView list;
    private ListViewAdapter customAdapter;
    public ArrayList<Transaction> CustomListViewValuesArr = new ArrayList<Transaction>();
    private int position;
    private String name = "Overview";
    private String strCategory;
    private String strDate;
    private String strAmount;
    private String strNote;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home_screen, null);

        list = (ListView)v.findViewById(R.id.listViewHome);

        setListData();

        // get data from the table by the MyListAdapter
        customAdapter = new ListViewAdapter(this.getActivity(),CustomListViewValuesArr, getResources());
        list.setAdapter(customAdapter);

        // long click listener implementation
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent,final View v, final int position, final long id) {
                try{
                    //dohvacam sve iz liste
                    strCategory = ((TextView) v.findViewById(R.id.text_category)).getText().toString();
                    //Toast.makeText(getActivity(), textCategoty, Toast.LENGTH_SHORT).show();
                    String textAmount = ((TextView) v.findViewById(R.id.text_amount)).getText().toString();
                    Float ftxt = Float.parseFloat(textAmount);
                    Integer itxt = Math.round(ftxt);
                    strAmount = itxt.toString();
                    //Toast.makeText(getActivity(), String.valueOf(itxt), Toast.LENGTH_SHORT).show();
                    strNote = ((TextView) v.findViewById(R.id.text_note)).getText().toString();
                    //Toast.makeText(getActivity(), textNote, Toast.LENGTH_SHORT).show();
                    strDate = ((TextView) v.findViewById(R.id.text_date)).getText().toString();
                    //Toast.makeText(getActivity(), textDate, Toast.LENGTH_SHORT).show();

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

                                                                    // brisanje preko id-a
                                                                    new Delete().from(Transaction.class).where("  id  = ?", getMyId()).execute();
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

                                    //TODO ovdje pozvati activity za editiranje unosa
                                    String id = getMyId();

                                    Intent myIntent = new Intent( getActivity(), CategoryActivity.class);
                                    myIntent.putExtra("amount",strAmount);
                                    myIntent.putExtra("category",strCategory);
                                    myIntent.putExtra("date",strDate);
                                    myIntent.putExtra("note", strNote);
                                    myIntent.putExtra("id", id);
                                    startActivity(myIntent);

                                    //Toast.makeText(getActivity(), "Id: " + getMyId(), Toast.LENGTH_SHORT).show();

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

    /** Metoda koja se implementira zbog sučelja NavigationItem.java,
     * dio dinamičkog prikaza stavki ladičara
     *
     * @return vraća ime stavke menija u ladičaru koju čini ovaj fragment
     */
    @Override
    public String getItemName() {
        return name;
    }


    /** Metoda koja se implementira zbog sučelja NavigationItem.java,
     * dio dinamičkog prikaza stavki ladičara
     *
     * @return vraća poziciju stavke menija u ladičaru koju čini ovaj fragment
     */
    @Override
    public int getPosition() {
        return position;
    }


    /** Metoda koja se implementira zbog sučelja NavigationItem.java,
     * dio dinamičkog prikaza stavki ladičara
     *
     * postavlja position ovisno o primljenom argumentu
     */
    @Override
    public void setPosition(int position) {
        this.position = position;
    }


    /** Metoda koja se implementira zbog sučelja NavigationItem.java,
     * dio dinamičkog prikaza stavki ladičara
     *
     * @return vraća referencu za ovaj fragment
     */
    @Override
    public android.app.Fragment getFragment() {
        return this;
    }


    /** Metoda koja se implementira zbog sučelja NavigationItem.java,
     * prima vrijednosti polja iz objekata categories i transactions
     * koji sadrže vrijednosti učitane iz baze podataka.
     */
    @Override
    public void loadData(ArrayList<Category> categories, ArrayList<Transaction> transactions) {



    }


    /** Metoda koja iz baze podataka dohvaća id vrijednost
     * za DB tablicu Transactions
     *
     * @return vraća string vrijednosti od redaka koji su
     * dobiveni upitom
     */
    public String getMyId(){

        List<Transaction> t = SQLiteUtils.rawQuery(Transaction.class,
                "SELECT Transactions.id from Transactions join Categories on Transactions.Category = Categories.id " +
                        "where note = ? and amount = ? and date = ? and Categories.name = ?"
                , new String[]{strNote,strAmount,strDate,strCategory});
        String idToEdit = "";
                for(int i=0; i<t.size(); i++){
                    idToEdit = t.get(i).getId().toString();
                }

        return idToEdit;

    }

    /** Metoda koja prilikom korisnikovog unosa nove
     * transakcije prosljeđuje te podatke u listu
     * koja prikazuje sve transakcije na početnom ekranu.
     */
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

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy.");
            String date = "";

            try {
                date = t.getDate();
            } catch (Exception ex) {
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
    public void onDestroyView() {
        super.onDestroyView();
        CustomListViewValuesArr.clear();
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DataLoader dl = new DbDataLoader();
        dl.LoadData(getActivity());
    }





}