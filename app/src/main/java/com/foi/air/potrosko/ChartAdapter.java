package com.foi.air.potrosko;

import com.foi.air.potrosko.db.Category;
import com.foi.air.potrosko.db.Transaction;

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


    public float[] getFloatValue(){yData = new float[]{ 5, 10, 15, 30, 40 }; return yData;}

    public String[] getStringValue() {xData = new String[] { "Sony", "Huawei", "LG", "Apple", "Samsung" };return xData; }

    //public float[] getTransactions(){ float[] yData = { 5, 10, 15, 30, 40 }; return yData;}

    //public String[] getCategories(){String[] xData = { "Sony", "Huawei", "LG", "Apple", "Samsung" }; return xData;}

    //public List<Transaction> getAllTransactions(){return transactions = Transaction.getAll();}

    //public List<Category> getAllCategories(){return categories = Category.getAll();}

    //public class Tuple2<T1,T2> {


}
