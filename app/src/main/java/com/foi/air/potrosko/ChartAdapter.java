package com.foi.air.potrosko;

import android.widget.Toast;

import com.activeandroid.util.SQLiteUtils;
import com.foi.air.potrosko.db.Category;
import com.foi.air.potrosko.db.Transaction;
import com.foi.air.potrosko.db.TransactionType;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marko Plaftarić on 30-Jan-16.
 */
public class ChartAdapter {

    //private List<Transaction> transactions;
    //private List<Category> categories;

    //novo
    private ArrayList<Transaction> chartTransactions;
    private ArrayList<Category> chartCategories;

    private float[] yData;
    private String[] xData ;

    public ChartAdapter(){}

    public ChartAdapter(float[] f,String[] s){
        this.yData = f;
        this.xData = s;
    }

    /** Funkcija za dohvaćanje vrijednosti troškova iz DB
     *
     * @return vraća polje s vrijednostima koje se iscrtavaju na Y os
     */
    public float[] getFloatValue(){
        String ttype = "expense";
        List<Transaction> t = SQLiteUtils.rawQuery(Transaction.class,
                "Select * from Transactions JOIN TransactionType on TransactionType = TransactionType.id where TransactionType.name=?"
                , new String[]{"expense"});

        yData = new float[t.size()];

        double sum = 0;
        for(int i=0; i<t.size(); i++){
            sum += t.get(i).getAmount();
        }
        DecimalFormat df = new DecimalFormat("###.##");
        for(int i=0; i<t.size(); i++){
            yData[i] = (float) ((t.get(i).getAmount()/sum)*100);
            //yData[i] = Float.parseFloat(df.format(t.get(i).getAmount()).toString());
        }

        //yData = new float[]{ 5, 10, 15, 30, 40 };
        return yData;
    }

    /** Funkcija za dohvaćanje vrijednosti imena kategorija iz DB
     *
     * @return vraća polje s vrijednostima naziva kategorije koje se iscrtavaju na X os
     */
    public String[] getStringValue() {
        List<Transaction> t = SQLiteUtils.rawQuery(Transaction.class,
                "Select * from Transactions JOIN TransactionType on TransactionType = TransactionType.id where TransactionType.name=?"
                , new String[]{"expense"});

        xData = new String[t.size()];
        for(int i=0; i<t.size(); i++){
            xData[i] = t.get(i).getCategoryName();
        }
        return xData;
    }

    //public float[] getTransactions(){ float[] yData = { 5, 10, 15, 30, 40 }; return yData;}

    //public String[] getCategories(){String[] xData = { "Sony", "Huawei", "LG", "Apple", "Samsung" }; return xData;}

    //public List<Transaction> getAllTransactions(){return transactions = Transaction.getAll();}

    //public List<Category> getAllCategories(){return categories = Category.getAll();}

    //public class Tuple2<T1,T2> {

}
