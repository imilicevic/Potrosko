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

public class HomeScreenFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_screen, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Srediti listView dummy podatke
        super.onActivityCreated(savedInstanceState);
        Log.d("CreatedActivity", "LaunchpadFragment");
        ListView plaftaList= (ListView) getActivity().findViewById(R.id.listViewHome);
        // Gets the ListView from the View list of the parent activity
        // Gets a CursorAdapter
        String[] ajs = {"Esi", "Mi", "Dobar", "Essi", "Mii", "Dobaaaar"};
        ArrayAdapter<String> plaftaAdapter;
        plaftaAdapter= new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, ajs);
        plaftaList.setAdapter(plaftaAdapter);
        // On list item clik show Toast msg with the content of item
        plaftaList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String ajs = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(getActivity(), ajs, Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }

}
