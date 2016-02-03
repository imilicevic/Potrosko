package com.foi.air.potrosko.chart;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foi.air.potrosko.core.NavigationItem;
import com.foi.air.potrosko.db.Category;
import com.foi.air.potrosko.db.Transaction;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by MarinFOI on 03-Feb-16.
 */
public class IncomeChartFragment extends Fragment implements NavigationItem {
    public static Fragment newInstance() {
        return new IncomeChartFragment();
    }

    private PieChart pChart;
    private int position;
    private String name = "Income Chart";


    /** Metoda koja je zadužena za grafički prikaz fragmenta za Piechart graf
     *
     * @return vraća pogled koji prikazuje grafičko sučelje iz xml koda fragmenta
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chart,container,false);

        pChart = (PieChart) v.findViewById(R.id.pie_chart);
        pChart.setDescription("");

        // radius of the center hole in percent of maximum radius
        pChart.setHoleRadius(55f);
        pChart.setTransparentCircleRadius(60f);

        // configure legend
        Legend l = pChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);

        // add data to chart
        addData();

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


    /** Funkcija za dohvaćanje vrijednosti troškova i kategorija iz DB,
     * dodavanje boja grafu te instanciranje samog grafa
     */
    private void addData() {

        // Dohvacam liste sa nazivima kategorija i vrijednostima unesenih transakcija u postocima
        ChartAdapter ch = new ChartAdapter();
        float[] yData = ch.getFloatValue("income");
        String[] xData = ch.getStringValue("income");

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        for (int i = 0; i < yData.length; i++) {
            yVals1.add(new Entry(yData[i], i));
        }

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < xData.length; i++)
            xVals.add(xData[i]);

        // create pie data set
        PieDataSet dataSet = new PieDataSet(yVals1, "Income by categories");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        // add many colors
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        // instantiate pie data object now
        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.GRAY);

        pChart.setData(data);

        // undo all highlights
        pChart.highlightValues(null);

        // update pie chart
        pChart.invalidate();

    }




}
