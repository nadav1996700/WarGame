package src.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.wargame_v2.R;
import src.Utils.My_SP;
import src.Utils.Utils;
import src.Utils.VictoryData;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Activity_Game extends AppCompatActivity {

    private static final int PLAYER1_TURN = 1;
    private static final int PLAYER2_TURN = 2;
    private static final int LARGE_ATTACK_POINTS = 50;
    private static final int MEDIUM_ATTACK_POINTS = 30;
    private static final int SMALL_ATTACK_POINTS = 10;
    private static final String PLAYER1_NAME = "Superman";
    private static final String PLAYER2_NAME = "Ironman";
    private static final int DELAY = 2000;

    private ProgressBar player1_PB;
    private ProgressBar player2_PB;
    private ImageView player1_imageView;
    private ImageView player2_imageView;
    private ImageView player1_cube;
    private ImageView player2_cube;
    private TextView attackType;
    private Button largeAttack_player1;
    private Button mediumAttack_player1;
    private Button smallAttack_player1;
    private Button largeAttack_player2;
    private Button mediumAttack_player2;
    private Button smallAttack_player2;
    private Button pick;
    private ArrayList<Button> player1_Buttons;
    private ArrayList<Button> player2_Buttons;
    private Utils utils = Utils.getInstance();
    private Random rand = new Random();
    private MediaPlayer mp;
    private LatLng mCurrentLocation;
    private int player1_counterAttack = 0;
    private int player2_counterAttack = 0;
    private int turn = 0;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            /* choose random attack */
            int number_of_attack = rand.nextInt(3) + 1;
            /* set sound according to attack*/
            makeSound(number_of_attack);
            /* update life bar */
            update_PB(number_of_attack);
            /* switch turn -> Turns off the current player's buttons and activates the opponent's */
            switchTurn();

            if (!gameOver())
                handler.postDelayed(this, DELAY);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        /* initialize values */
        setValues();
        /* add buttons to lists */
        initialize_player1_list();
        initialize_player2_list();

        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // start game only if players didn't get the same value in dice
                if (choose_who_start()) {
                    /* enable buttons of the player that won in dice */
                    Enable_buttons();
                    /* start game */
                    handler.postDelayed(runnable, DELAY);
                }
            }
        });
    }

    /* enable buttons of the player that won in dice */
    private void Enable_buttons() {
        if (turn == PLAYER1_TURN) {
            for (Button btn : player1_Buttons)
                btn.setEnabled(true);
        } else if (turn == PLAYER2_TURN) {
            for (Button btn : player2_Buttons)
                btn.setEnabled(true);
        }
    }

    private boolean choose_who_start() {
        /* drop the cubes and choose random numbers */
        int player1_number = diceCube(player1_cube);
        int player2_number = diceCube(player2_cube);
        if (player1_number > player2_number) {
            turn = PLAYER1_TURN;
            pick.setEnabled(false);
            return true;
        } else if (player2_number > player1_number) {
            turn = PLAYER2_TURN;
            pick.setEnabled(false);
            return true;
        }
        // even numbers
        return false;
    }

    /* make random number for each player and update the photo of the cubes */
    private int diceCube(ImageView cube_image) {
        int random = rand.nextInt(6) + 1;
        /* change photo of Image View */
        change_IV(cube_image, random);
        return random;
    }

    /* change cube photo using glide */
    private void change_IV(ImageView cube_image, int number) {
        switch (number) {
            case 1:
                utils.setImage(cube_image, ContextCompat.getDrawable(this, R.drawable.dice_1));
                break;
            case 2:
                utils.setImage(cube_image, ContextCompat.getDrawable(this, R.drawable.dice_2));
                break;
            case 3:
                utils.setImage(cube_image, ContextCompat.getDrawable(this, R.drawable.dice_3));
                break;
            case 4:
                utils.setImage(cube_image, ContextCompat.getDrawable(this, R.drawable.dice_4));
                break;
            case 5:
                utils.setImage(cube_image, ContextCompat.getDrawable(this, R.drawable.dice_5));
                break;
            case 6:
                utils.setImage(cube_image, ContextCompat.getDrawable(this, R.drawable.dice_6));
                break;
        }
    }

    /* make appropriate sound according to attack points */
    private void makeSound(int number_of_attack) {
        switch (number_of_attack) {
            case 1:
                mp = MediaPlayer.create(this, R.raw.small_points_sound);
                break;
            case 2:
                mp = MediaPlayer.create(this, R.raw.medium_points_sound);
                break;
            case 3:
                mp = MediaPlayer.create(this, R.raw.large_points_sound);
                break;
            default:
                break;
        }
        mp.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showAlertDialog();
    }

    private void showAlertDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Do you wish to continue?");
        alert.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                /* start game only when turn has been set*/
                if (!pick.isEnabled())
                    handler.postDelayed(runnable, DELAY);
            }
        });
        alert.setNegativeButton("Home", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alert.create().show();
    }

    /* update life bar */
    private void update_PB(int number_of_attack) {
        if (turn == PLAYER1_TURN) {
            setProgressBar(number_of_attack, player1_Buttons, player2_PB);
            (player1_Buttons.get(number_of_attack - 1)).setEnabled(false);
        }
        else {
            setProgressBar(number_of_attack, player2_Buttons, player1_PB);
            (player2_Buttons.get(number_of_attack - 1)).setEnabled(false);
        }
    }

    private void setProgressBar(int number_of_attack, ArrayList<Button> list, ProgressBar opponent_pb) {

        switch (number_of_attack) {
            case 1:
                attackType.setText(SMALL_ATTACK_POINTS + " pt attack");
                decreasePB(opponent_pb, SMALL_ATTACK_POINTS);
                break;
            case 2:
                attackType.setText(MEDIUM_ATTACK_POINTS + " pt attack");
                decreasePB(opponent_pb, MEDIUM_ATTACK_POINTS);
                break;
            case 3:
                attackType.setText(LARGE_ATTACK_POINTS + " pt attack");
                decreasePB(opponent_pb, LARGE_ATTACK_POINTS);
                break;
        }
    }

    /* decrease progress bar by points */
    private void decreasePB(ProgressBar pb, int points) {
        pb.setProgress(pb.getProgress() - points);
        if (pb.getProgress() < 40) {
            pb.setProgressDrawable(ContextCompat.getDrawable(this, R.drawable.red_progress_bar));
        }
    }

    /* if game over -> send victory name to Victory Activity and switch activity */
    private boolean gameOver() {
        if (player1_PB.getProgress() == 0 || player2_PB.getProgress() == 0) {
            // release resources of MediaPlayer
            mp.release();
            // get location
            mCurrentLocation = utils.getLocation(this);
            // save victory data
            saveVictoryData();
            // open victory activity and finish
            openVictoryActivity();
            finish();
            return true;
        }
        return false;
    }

    private void saveVictoryData() {
        // save data of winner in sharedPreferences
        if (turn == PLAYER1_TURN)  // player 2 won
            saveData(PLAYER2_NAME, player2_counterAttack);
        else                       // player 1 won
            saveData(PLAYER1_NAME, player1_counterAttack);
    }

    /* open victory screen after game over */
    private void openVictoryActivity() {
        Intent intent = new Intent(Activity_Game.this, Activity_Victory.class);
        if (turn == PLAYER1_TURN) { // player 2 won
            intent.putExtra(Activity_Victory.EXTRA_KEY_VICTORY, PLAYER2_NAME);
            intent.putExtra(Activity_Victory.EXTRA_KEY_ATTACKS, player2_counterAttack);
        }
        else {                      // player 1 won
            intent.putExtra(Activity_Victory.EXTRA_KEY_VICTORY, PLAYER1_NAME);
            intent.putExtra(Activity_Victory.EXTRA_KEY_ATTACKS, player1_counterAttack);
        }
        startActivity(intent);
    }

    /* save data of winner to sharedPreferences */
    private void saveData(String name, int counterOfAttacks) {
        // load data from sharedPreferences
        My_SP sp = My_SP.getInstance();
        ArrayList<VictoryData> list = sp.loadData();
        if (list.size() == 10) {
            // sort array by victories
            Collections.sort(list, new Comparator<VictoryData>() {
                @Override
                public int compare(VictoryData v1, VictoryData v2) {
                    return v1.get_attacks() - v2.get_attacks();
                }
            });
            // replace object at the last place
            if (list.get(list.size() - 1).get_attacks() > counterOfAttacks) {
                list.remove(list.get(list.size() - 1));
                list.add(new VictoryData(name, counterOfAttacks, mCurrentLocation));
            }
        } else
            // add new object - list size is less than 10
            list.add(new VictoryData(name, counterOfAttacks, mCurrentLocation));
        // save back to sharedPreferences
        sp.saveData(list);
    }

    /* add buttons of player_1 to list */
    private void initialize_player1_list() {
        player1_Buttons = new ArrayList<>();
        player1_Buttons.add(smallAttack_player1);
        player1_Buttons.add(mediumAttack_player1);
        player1_Buttons.add(largeAttack_player1);
    }

    /* add buttons of player_2 to list */
    private void initialize_player2_list() {
        player2_Buttons = new ArrayList<>();
        player2_Buttons.add(smallAttack_player2);
        player2_Buttons.add(mediumAttack_player2);
        player2_Buttons.add(largeAttack_player2);
    }

    private void setValues() {
        player1_PB = findViewById(R.id.Game_PB_player1);
        player2_PB = findViewById(R.id.Game_PB_player2);
        largeAttack_player1 = findViewById(R.id.Game_BTN_50pt_player1);
        mediumAttack_player1 = findViewById(R.id.Game_BTN_30pt_player1);
        smallAttack_player1 = findViewById(R.id.Game_BTN_10pt_player1);
        largeAttack_player2 = findViewById(R.id.Game_BTN_50pt_player2);
        mediumAttack_player2 = findViewById(R.id.Game_BTN_30pt_player2);
        smallAttack_player2 = findViewById(R.id.Game_BTN_10pt_player2);
        player1_imageView = findViewById(R.id.Game_IV_player1);
        player2_imageView = findViewById(R.id.Game_IV_player2);
        player1_cube = findViewById(R.id.Game_IV_player1_Cube);
        player2_cube = findViewById(R.id.Game_IV_player2_Cube);
        attackType = findViewById(R.id.Game_LBL_attackType);
        pick = findViewById(R.id.Game_BTN_pick);
        setImages();

        /* initialize value of progress bars to 100 */
        player1_PB.setProgress(100);
        player2_PB.setProgress(100);
    }

    private void setImages() {
        /* set images using glide library*/
        utils.setImage(player1_imageView, ContextCompat.getDrawable(this, R.drawable.superman));
        utils.setImage(player2_imageView, ContextCompat.getDrawable(this, R.drawable.iron));
        utils.setImage(player1_cube, ContextCompat.getDrawable(this, R.drawable.dice_1));
        utils.setImage(player2_cube, ContextCompat.getDrawable(this, R.drawable.dice_1));
    }

    private void switchTurn() {
        ArrayList<Button> switchTo;
        ArrayList<Button> current;
        if (turn == PLAYER1_TURN) {
            current = player1_Buttons;
            switchTo = player2_Buttons;
        } else {
            current = player2_Buttons;
            switchTo = player1_Buttons;
        }
        makeSwitch(current, switchTo);
    }

    private void makeSwitch(ArrayList<Button> current, ArrayList<Button> switchTo) {
        for (Button btn : current)
            btn.setEnabled(false);
        for (Button btn : switchTo)
            btn.setEnabled(true);
        changeTurn();
    }

    /* change turn and increase counter attack */
    private void changeTurn() {
        if (turn == PLAYER1_TURN) {
            turn = PLAYER2_TURN;
            player1_counterAttack += 1;
        } else {
            turn = PLAYER1_TURN;
            player2_counterAttack += 1;
        }
    }
}