package com.foi.air.potrosko.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.foi.air.potrosko.ChartAdapter;
import com.foi.air.potrosko.R;
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
 * Created by Marko Plaftarić on 30-Jan-16.
 */

public class ChartScreenFragment extends Fragment implements NavigationItem{

    public static Fragment newInstance() {
        return new ChartScreenFragment();
    }

    private PieChart pChart;
    private int position;
    private String name = "ChartsKas";

    // premješteno u ChartAdapter klasu
    //private float[] yData = { 5, 10, 15, 30, 40 };
    //private String[] xData = { "Sony", "Huawei", "LG", "Apple", "Samsung" };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chart_screen,container,false);

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

/*      //Toast podataka ako će biti potrebno
        // set a chart value selected listener
        pChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                // display msg when value selected
                if (e == null)
                    return;

                Toast.makeText(getActivity(), xData[e.getXIndex()] + " = " + e.getVal() + "%", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
*/
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


    private void addData() {

        // Dohvacam liste sa nazivima kategorija i vrijednostima unesenih transakcija u postocima
        ChartAdapter ch = new ChartAdapter();
        float[] yData = ch.getFloatValue();
        String[] xData = ch.getStringValue();

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i = 0; i < yData.length; i++)
            yVals1.add(new Entry(yData[i], i));

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < xData.length; i++)
            xVals.add(xData[i]);

        // create pie data set
        PieDataSet dataSet = new PieDataSet(yVals1, "Market Share");
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
