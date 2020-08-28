package com.example.wargame_v2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;

public class RankActivity extends AppCompatActivity {

    /*
    private Fragment MapsFragment;
    private Fragment top10_Fragment;

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        /*
        MapsFragment = new Fragment();
        top10_Fragment = new Fragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rank_frame_map, MapsFragment)
                .replace(R.id.rank_frame_rankList, top10_Fragment)
                .commit();

         */

    }
}