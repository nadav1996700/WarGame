package com.example.wargame_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button startGame;
    private Button rank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // init sp
        My_SP sp = My_SP.initHelper(HomeActivity.this);

        // set value of buttons
        setValues();

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(MainActivity.class);
            }
        });
        rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //openActivity(RankActivity.class);
                Intent intent = new Intent(HomeActivity.this, TopTenActivity.class);
                startActivity(intent);
            }
        });
    }

    private void openActivity(Class activity_class) {
        Intent intent = new Intent(HomeActivity.this, activity_class);
        startActivity(intent);
    }

    private void setValues() {
        startGame = findViewById(R.id.home_BTN_newGame);
        rank = findViewById(R.id.home_BTN_rank);
    }
}