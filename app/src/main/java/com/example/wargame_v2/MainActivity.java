package com.example.wargame_v2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

//glide
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_VICTORY = "com.example.application.example.EXTRA_VICTORY";
    private static final int SUPERMAN_TURN = 1;
    private static final int IRONMAN_TURN = 2;
    private static final int LARGE_ATTACK_POINTS = 50;
    private static final int MEDIUM_ATTACK_POINTS = 30;
    private static final int SMALL_ATTACK_POINTS = 10;

    private ProgressBar superman_PB;
    private ProgressBar ironman_PB;
    private ImageView superman_image;
    private ImageView ironman_image;
    private ImageView superman_cube;
    private ImageView ironman_cube;
    private Button largeAttack_superman;
    private Button mediumAttack_superman;
    private Button smallAttack_superman;
    private Button largeAttack_ironman;
    private Button mediumAttack_ironman;
    private Button smallAttack_ironman;
    private Button pick;
    private ArrayList<Button> superman_Buttons;
    private ArrayList<Button> ironman_Buttons;
    private Random rand = new Random();
    private int turn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* initialize values */
        setValues();

        /* add buttons to lists */
        initialize_superman_list();
        initialize_ironman_list();

        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choose_who_start();
                /* disable buttons of the player that lost in the dice */
                disable_buttons();
                /* start the game */
                //playGame();
            }
        });
    }

    private void disable_buttons() {
        if(turn == SUPERMAN_TURN) {
            for (Button btn : ironman_Buttons)
                btn.setEnabled(false);
        } else {
            for (Button btn : superman_Buttons)
                btn.setEnabled(false);
        }
    }

    private void choose_who_start() {
        /* drop the cubes and choose random numbers */
        int superman_number = diceCube(superman_cube);
        int ironman_number = diceCube(ironman_cube);
        if(superman_number > ironman_number) {
            turn = SUPERMAN_TURN;
            pick.setEnabled(false);
        }
        else if (ironman_number > superman_number) {
            turn  = IRONMAN_TURN;
            pick.setEnabled(false);
        }
    }

    /* make random number for each player and update the photo of the cubes */
    private int diceCube(ImageView cube_image) {
        int random = rand.nextInt(6) + 1;
        /* change photo of Image View */
        change_IV(cube_image, random);
        return random;
    }

    private void change_IV(ImageView cube_image, int number) {
        switch(number) {
            case 1:
                cube_image.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dice_1));
                break;
            case 2:
                cube_image.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dice_2));
                break;
            case 3:
                cube_image.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dice_3));
                break;
            case 4:
                cube_image.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dice_4));
                break;
            case 5:
                cube_image.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dice_5));
                break;
            case 6:
                cube_image.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dice_6));
                break;
        }
    }

    private void playGame() {
        new Thread (new Runnable() {
            @Override
            public void run() {
                do {
                    int number_of_attack = rand.nextInt(3) + 1;
                    setProgressBar(number_of_attack);
                    switchTurn();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while(!gameOver());
            }
        }).start();

    }

    private void setProgressBar(int number_of_attack) {
        if (turn == SUPERMAN_TURN) {
            switch (number_of_attack) {
                case 1:
                    smallAttack_superman.setEnabled(false);
                    decreasePB(ironman_PB, SMALL_ATTACK_POINTS);
                    break;
                case 2:
                    mediumAttack_superman.setEnabled(false);
                    decreasePB(ironman_PB, MEDIUM_ATTACK_POINTS);
                    break;
                case 3:
                    largeAttack_superman.setEnabled(false);
                    decreasePB(ironman_PB, LARGE_ATTACK_POINTS);
                    break;
            }
        } else if (turn == IRONMAN_TURN) {
            switch (number_of_attack) {
                case 1:
                    smallAttack_ironman.setEnabled(false);
                    decreasePB(superman_PB, SMALL_ATTACK_POINTS);
                    break;
                case 2:
                    mediumAttack_ironman.setEnabled(false);
                    decreasePB(superman_PB, MEDIUM_ATTACK_POINTS);
                    break;
                case 3:
                    largeAttack_ironman.setEnabled(false);
                    decreasePB(superman_PB, LARGE_ATTACK_POINTS);
                    break;
            }
        }
    }

    private void decreasePB(ProgressBar pb, int points) {
        pb.setProgress(pb.getProgress() - points);
        if(pb.getProgress() < 40) {
            pb.setProgressDrawable(ContextCompat.getDrawable(this, R.drawable.red_progress_bar));
        }
    }

    /* if game over -> send victory name to Victory Activity and switch activity */
    private boolean gameOver() {
        if(superman_PB.getProgress() == 0 || ironman_PB.getProgress() == 0) {
            openVictoryActivity();
            finish();
            return true;
        }
        return false;
    }

    private void openVictoryActivity() {
        Intent intent = new Intent(MainActivity.this, VictoryActivity.class);
        if(turn == SUPERMAN_TURN)
            intent.putExtra(EXTRA_VICTORY, "Ironman");
        else
            intent.putExtra(EXTRA_VICTORY, "Superman");
        startActivity(intent);
    }

    private void initialize_superman_list() {
        superman_Buttons = new ArrayList<>();
        superman_Buttons.add(largeAttack_superman);
        superman_Buttons.add(mediumAttack_superman);
        superman_Buttons.add(smallAttack_superman);
    }

    private void initialize_ironman_list() {
        ironman_Buttons = new ArrayList<>();
        ironman_Buttons.add(largeAttack_ironman);
        ironman_Buttons.add(mediumAttack_ironman);
        ironman_Buttons.add(smallAttack_ironman);
    }

    private void setValues() {
        superman_PB = findViewById(R.id.main_PB_supermanPB);
        ironman_PB = findViewById(R.id.main_PB_ironmanPB);
        largeAttack_superman = findViewById(R.id.main_BTN_50pt_superman);
        mediumAttack_superman = findViewById(R.id.main_BTN_30pt_superman);
        smallAttack_superman = findViewById(R.id.main_BTN_10pt_superman);
        largeAttack_ironman = findViewById(R.id.main_BTN_50pt_ironman);
        mediumAttack_ironman = findViewById(R.id.main_BTN_30pt_ironman);
        smallAttack_ironman = findViewById(R.id.main_BTN_10pt_ironman);
        pick = findViewById(R.id.main_BTN_pick);
        superman_image = findViewById(R.id.main_IV_superman);
        ironman_image = findViewById(R.id.main_IV_ironman);
        superman_cube = findViewById(R.id.main_IV_supermanCube);
        ironman_cube = findViewById(R.id.main_IV_ironmanCube);

        /* set images using glide library*/
        setImages();

        /* initialize value of progress bars to 100 */
        superman_PB.setProgress(100);
        ironman_PB.setProgress(100);
    }

    private void setImages() {
        /* set images of players */
        Glide.with(MainActivity.this)
                .load("")
                .placeholder(R.drawable.superman)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(superman_image);

        Glide.with(MainActivity.this)
                .load("")
                .placeholder(R.drawable.iron)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(ironman_image);

        /* set images of cubes */
        Glide.with(MainActivity.this)
                .load("")
                .placeholder(R.drawable.dice_1)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(superman_cube);

        Glide.with(MainActivity.this)
                .load("")
                .placeholder(R.drawable.dice_2)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(ironman_cube);
    }

    private void switchTurn() {
        ArrayList<Button> switchTo;
        ArrayList<Button> current;
        if (turn == SUPERMAN_TURN) {
            current = superman_Buttons;
            switchTo = ironman_Buttons;
        }
        else {
            current = ironman_Buttons;
            switchTo = superman_Buttons;
        }
        makeSwitch(current, switchTo);
    }

    private void makeSwitch(ArrayList<Button> current, ArrayList<Button> switchTo) {
        for(Button btn: current)
            btn.setEnabled(false);
        for(Button btn: switchTo)
            btn.setEnabled(true);
        changeTurn();
    }

    private void changeTurn() {
        if (turn == SUPERMAN_TURN)
            turn = IRONMAN_TURN;
        else
            turn = SUPERMAN_TURN;
    }
}