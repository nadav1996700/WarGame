package com.example.wargame_v2.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.wargame_v2.Utils.Utils;
import com.example.wargame_v2.R;

public class Activity_Home extends AppCompatActivity {

    private Button startGame;
    private Button rank;
    private ImageView background_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // init Glide
        Utils glide_util = Utils.getInstance();

        // set value of buttons
        setValues();
        // set background image
        glide_util.setImage(background_img, ContextCompat.getDrawable(this, R.drawable.home_background));

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_Home.this, Activity_Game.class));
            }
        });
        rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_Home.this, Activity_Rank.class));
            }
        });
    }

    private void setValues() {
        startGame = findViewById(R.id.home_BTN_newGame);
        rank = findViewById(R.id.home_BTN_rank);
        background_img = findViewById(R.id.home_IV_background);
    }
}