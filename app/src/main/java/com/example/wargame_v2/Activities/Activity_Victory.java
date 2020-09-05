package com.example.wargame_v2.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wargame_v2.R;
import com.example.wargame_v2.Utils.Utils;

public class Activity_Victory extends AppCompatActivity {

    public static final String EXTRA_KEY_VICTORY = "EXTRA_VICTORY";
    private TextView victory_TV;
    private Button newGame;
    private Button home_screen;
    private ImageView background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory);

        // initialize variables
        setValues();
        // init Glide
        Utils glide_util = Utils.getInstance();
        // set background image
        glide_util.setImage(background, ContextCompat.getDrawable(this, R.drawable.winner_podium));
        // get victory name and set TextView
        Intent input = getIntent();
        String victory = input.getStringExtra(EXTRA_KEY_VICTORY);
        victory_TV.setText(victory + " WON!");

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_Victory.this, Activity_Game.class));
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
        background = findViewById(R.id.victory_IV_background);
    }
}