package com.example.wargame_v2.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.wargame_v2.R;
import com.example.wargame_v2.fragments.Fragment_Map;
import com.example.wargame_v2.fragments.Fragment_top10;

public class Activity_Rank extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        initFragments();
    }

    private void initFragments() {
        Fragment_Map fragment_map = Fragment_Map.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.rank_LAY_map, fragment_map);
        transaction.commit();

        Fragment_top10 fragment_top10 = Fragment_top10.newInstance();
        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
        transaction2.replace(R.id.rank_LAY_list, fragment_top10);
        transaction.commit();
    }
}