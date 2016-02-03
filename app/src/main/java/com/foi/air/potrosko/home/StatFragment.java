/*
package com.foi.air.potrosko.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foi.air.potrosko.core.NavigationItem;
import com.foi.air.potrosko.db.Category;
import com.foi.air.potrosko.db.Transaction;

import java.util.ArrayList;

*/
/**
 * Created by Ivan on 3.2.2016..
 *//*

public class StatFragment extends Fragment implements NavigationItem {


    private int position;
    private String name = "Statistics";

    */
/**
 * Metoda koja je zadužena za grafički prikaz fragmenta za Piechart graf
 *
 * @return vraća pogled koji prikazuje grafičko sučelje iz xml koda fragmenta
 *//*

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(com.foi.air.potrosko.chart.R.layout.fragment_stat_screen, container, false);

        //add data to stats
        addStatData();

        return v;
    }


    */
/**
 * Metoda koja se implementira zbog sučelja NavigationItem.java,
 * dio dinamičkog prikaza stavki ladičara
 *
 * @return vraća ime stavke menija u ladičaru koju čini ovaj fragment
 *//*

    @Override
    public String getItemName() {
        return name;
    }


    */
/**
 * Metoda koja se implementira zbog sučelja NavigationItem.java,
 * dio dinamičkog prikaza stavki ladičara
 *
 * @return vraća poziciju stavke menija u ladičaru koju čini ovaj fragment
 *//*

    @Override
    public int getPosition() {
        return position;
    }


    */
/**
 * Metoda koja se implementira zbog sučelja NavigationItem.java,
 * dio dinamičkog prikaza stavki ladičara
 * <p/>
 * postavlja position ovisno o primljenom argumentu
 *//*

    @Override
    public void setPosition(int position) {
        this.position = position;
    }


    */
/**
 * Metoda koja se implementira zbog sučelja NavigationItem.java,
 * dio dinamičkog prikaza stavki ladičara
 *
 * @return vraća referencu za ovaj fragment
 *//*

    @Override
    public android.app.Fragment getFragment() {
        return this;
    }


    */
/**
 * Metoda koja se implementira zbog sučelja NavigationItem.java,
 * prima vrijednosti polja iz objekata categories i transactions
 * koji sadrže vrijednosti učitane iz baze podataka.
 *//*

    @Override
    public void loadData(ArrayList<Category> categories, ArrayList<Transaction> transactions) {

    }

    private void addStatData(){



    }


}*/

