package com.example.wargame_v2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wargame_v2.Utils.My_SP;
import com.example.wargame_v2.R;

public class Activity_Home extends AppCompatActivity {

    private Button startGame;
    private Button rank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // init sp
        My_SP sp = My_SP.initHelper(Activity_Home.this);

        // set value of buttons
        setValues();

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openActivity(Activity_Game.class);
            }
        });
        rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(Activity_Rank.class);
            }
        });
    }

    private void openActivity(Class activity_class) {
        Intent intent = new Intent(Activity_Home.this, activity_class);
        startActivity(intent);
    }

    private void setValues() {
        startGame = findViewById(R.id.home_BTN_newGame);
        rank = findViewById(R.id.home_BTN_rank);
    }
}