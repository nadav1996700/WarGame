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
    private TextView place1;
    private TextView place2;
    private TextView place3;
    private TextView place4;
    private TextView place5;
    private TextView place6;
    private TextView place7;
    private TextView place8;
    private TextView place9;
    private TextView place10;
    private ArrayList<TextView> tv_list;

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

        setValues(view);
        // add text views to tv_list
        initializeList();
        // get data from sharedPreferences
        setData();
        return view;
    }

    private void initializeList() {
        tv_list = new ArrayList<>();
        tv_list.add(place1);
        tv_list.add(place2);
        tv_list.add(place3);
        tv_list.add(place4);
        tv_list.add(place5);
        tv_list.add(place6);
        tv_list.add(place7);
        tv_list.add(place8);
        tv_list.add(place9);
        tv_list.add(place10);
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
            tv_list.get(i).setText(tv_list.get(i).getText() + list.get(i).toString());
        }
    }

    private void setValues(View view) {
        place1 = view.findViewById(R.id.top_ten_tv_place1);
        place2 = view.findViewById(R.id.top_ten_tv_place2);
        place3 = view.findViewById(R.id.top_ten_tv_place3);
        place4 = view.findViewById(R.id.top_ten_tv_place4);
        place5 = view.findViewById(R.id.top_ten_tv_place5);
        place6 = view.findViewById(R.id.top_ten_tv_place6);
        place7 = view.findViewById(R.id.top_ten_tv_place7);
        place8 = view.findViewById(R.id.top_ten_tv_place8);
        place9 = view.findViewById(R.id.top_ten_tv_place9);
        place10 = view.findViewById(R.id.top_ten_tv_place10);
    }
}