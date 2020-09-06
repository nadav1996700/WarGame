package com.example.wargame_v2.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
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

        Fragment_Map fragment_map = Fragment_Map.newInstance();
        Fragment_top10 fragment_top10 = Fragment_top10.newInstance();
        initFragments(fragment_map, R.id.rank_LAY_map);
        initFragments(fragment_top10, R.id.rank_LAY_list);
    }

    private void initFragments(Fragment fragment, int layout) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(layout, fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed(){
        finish();
    }
}