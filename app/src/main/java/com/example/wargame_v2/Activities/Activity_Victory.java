package com.example.wargame_v2.Activities;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wargame_v2.R;

public class Activity_Victory extends AppCompatActivity {

    public static final String EXTRA_KEY_VICTORY = "EXTRA_VICTORY";
    private TextView victory_TV;
    private Button newGame;
    private Button home_screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory);

        // initialize variables
        setValues();

        // get victory name and set TextView
        Intent input = getIntent();
        String victory = input.getStringExtra(EXTRA_KEY_VICTORY);
        victory_TV.setText(victory + " WON!");

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(Activity_Game.class);
                finish();
            }
        });

        home_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setValues() {
        newGame = findViewById(R.id.victory_BTN_newGame);
        home_screen = findViewById(R.id.victory_BTN_home);
        victory_TV = findViewById(R.id.victory_LBL_title);
    }

    private void openActivity(Class activity) {
        Intent intent = new Intent(Activity_Victory.this, activity);
        startActivity(intent);
    }
}