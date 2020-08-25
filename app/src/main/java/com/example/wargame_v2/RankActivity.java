package com.example.wargame_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;

public class RankActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        My_SP.initHelper(this);
        My_SP sp = My_SP.getInstance();
        ArrayList<VictoryData> rank_list;

    }
}