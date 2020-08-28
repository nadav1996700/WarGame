package com.example.wargame_v2;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class VictoryActivity extends AppCompatActivity {

    public static final String EXTRA_KEY_VICTORY = "EXTRA_VICTORY";
    private TextView victory_TV;
    private Button newGame;
    private Button home_screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory);

        /*
        TextView tv = findViewById(R.id.textView);
        ArrayList<VictoryData> list = My_SP.getInstance().loadData();
        tv.setText("" + list.get(0).get_location());
         */
        // initialize variables
        setValues();

        // get victory name and set TextView
        Intent input = getIntent();
        String victory = input.getStringExtra(EXTRA_KEY_VICTORY);
        victory_TV.setText(victory + " WON!");

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(MainActivity.class);
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
        victory_TV = findViewById(R.id.victory_TV_title);
    }

    private void openActivity(Class activity) {
        Intent intent = new Intent(VictoryActivity.this, activity);
        startActivity(intent);
    }
}