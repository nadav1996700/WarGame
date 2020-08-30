package com.example.wargame_v2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.wargame_v2.R;

public class Activity_Rank extends AppCompatActivity {

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