package com.example.wargame_v2.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wargame_v2.R;
import com.example.wargame_v2.Utils.My_SP;
import com.example.wargame_v2.Utils.VictoryData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Fragment_top10 extends Fragment {

    protected View view;
    private TextView places;

    public static Fragment_top10 newInstance() {
        Fragment_top10 fragment = new Fragment_top10();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view == null)
            view = inflater.inflate(R.layout.fragment_top10, container, false);

        places = view.findViewById(R.id.top_ten_places);
        // get data from sharedPreferences
        setData();
        return view;
    }

    private void setData() {
        My_SP sp = My_SP.getInstance();
        ArrayList<VictoryData> list = sp.loadData();
        Collections.sort(list, new Comparator<VictoryData>() {
            @Override
            public int compare(VictoryData data1, VictoryData data2) {
                return data1.get_attacks() - data2.get_attacks();
            }
        });
        for(int i = 0; i < list.size(); i++) {
            if(i == 0)
                places.setText(places.getText() + "" + (i+1) + ". " + list.get(i).toString());
            else
                places.setText(places.getText() + "\n" + (i+1) + ". " + list.get(i).toString());
        }
    }
}