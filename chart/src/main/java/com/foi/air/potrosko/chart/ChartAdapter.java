package com.foi.air.potrosko.chart;

import android.widget.Toast;

import com.activeandroid.util.SQLiteUtils;
import com.foi.air.potrosko.core.DbDataLoader;
import com.foi.air.potrosko.db.Category;
import com.foi.air.potrosko.db.Transaction;
import com.foi.air.potrosko.db.TransactionType;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


/**Klasa koja sadrži funkcije za dohvaćanje imena kategorija i vrijednosti
 * troškova iz baze podataka te ih prosljeđuje klasi ExpenseChartFragment.java
 * koja koristi te vrijednosti za crtanje Piechart grafa
 */
public class ChartAdapter {

    private ArrayList<Transaction> chartTransactions;
    private ArrayList<Category> chartCategories;

    private float[] yData;
    private String[] xData ;
    private int[] indexi = {};

    /** Konstruktor za ChartAdapter koji
     * ne prima argumente
     */
    public ChartAdapter(){}

    /** Konstruktor za ChartAdapter koji
     * kao argumente prima polje float vrijednosti
     * i polje stringova i tim vrijednostima puni
     * xData i yData polja
     */
    public ChartAdapter(float[] f,String[] s){
        this.yData = f;
        this.xData = s;
    }

    /** Funkcija za dohvaćanje vrijednosti troškova iz DB
     *
     * @return vraća polje s vrijednostima koje se iscrtavaju na Y os
     */
    public float[] getFloatValue(String input){

        DbDataLoader dl = new DbDataLoader();
        List<Transaction> t = dl.LoadChartData(input);

                yData = new float[t.size()];

        double sum = 0, amount = 0;
        for(int i=0; i<t.size(); i++){
            amount = t.get(i).getAmount();

            if(amount < 0)
                amount = amount * -1;

            sum += amount;
        }
        DecimalFormat df = new DecimalFormat("###.##");
        for(int i=0; i<t.size(); i++){
            amount = t.get(i).getAmount();
            if(amount < 0)
                amount = amount * -1;
            yData[i] = (float) ((amount/sum)*100);
            //yData[i] = Float.parseFloat(df.format(t.get(i).getAmount()).toString());
        }

        return yData;
    }

    /** Funkcija za dohvaćanje vrijednosti imena kategorija iz DB
     *
     * @return vraća polje s vrijednostima naziva kategorije koje se iscrtavaju na X os
     */
    public String[] getStringValue(String input) {

        DbDataLoader dl = new DbDataLoader();
        List<Transaction> t = dl.LoadChartData(input);

        xData = new String[t.size()];
        //String[] tempStrA = new String[t.size()];

        for(int i=0; i<t.size(); i++){
            xData[i] = t.get(i).getCategoryName();
        }

        /*
        //ovdje nabavim sva unikatna imena kategorija
        String[] unique = new HashSet<String>(Arrays.asList(xData)).toArray(new String[0]);

        for(int i=0; i<t.size(); i++){
            stringContainsItemFromList(unique[i],xData);
        }

        */
        return xData;
    }
    /*
    //funkcija prihvaca string i trazi dali se taj string pojavljuje u polju
    //pa sprema indekse na kojim mjestima se pojavila. Trenutno ne radi kako bi trebalo
    //jer jer bi trebao generirati dinamicki polja indeksa, pa preko njih sumirati drugu listu...
    private void  stringContainsItemFromList(String inputString, String[] items)
    {
        int flag = 0;
        for(int i =0; i < items.length; i++)
        {
            if(inputString.contains(items[i]))
            {

                indexi[flag] = i;
                flag++;

            }
        }

    }
    */


}
