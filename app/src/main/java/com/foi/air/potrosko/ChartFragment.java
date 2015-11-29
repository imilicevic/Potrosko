package com.foi.air.potrosko;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ChartFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Srediti listView dummy podatke
        super.onActivityCreated(savedInstanceState);
        Log.d("CreatedActivity", "LaunchpadFragment");
        ListView itemList= (ListView) getActivity().findViewById(R.id.listViewChart);
        // Gets the ListView from the View list of the parent activity
        // Gets a CursorAdapter
        String[] str = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"};
        ArrayAdapter<String> itemAdapter;
        itemAdapter= new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, str);
        itemList.setAdapter(itemAdapter);
        // On list item clik show Toast msg with the content of item
        itemList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String sky = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(getActivity(), sky, Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }
}
