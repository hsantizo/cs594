package com.hensan.pig;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;


public class Player2 extends ActionBarActivity {
    private FrameLayout die1, die2;
    private Button roll, hold;
    private int val1, val2, points, round, score, p1Score;
    private TextView pl1, pl2, roundNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player2);

        p1Score = getIntent().getIntExtra("p1Score", 0);
        score = getIntent().getIntExtra("p2Score", 0);
        pl1 = (TextView) findViewById(R.id.p1);
        pl1.setText("P1: " + p1Score);
        pl2 = (TextView) findViewById(R.id.p2);
        pl2.setText("P2: " + score);
        roundNum = (TextView) findViewById(R.id.round);
        roundNum.setText("Round: " + round);
        isWinner();

        roll = (Button) findViewById(R.id.button);
        roll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                rollDice();

                if(val1 == 1 && val2 == 1){
                    p1Score += score;
                    round = 0;
                    score = 0;
                    nextPlayer();
                }
                else if(val1 == 1 || val2 == 1){
                    round = 0;
                    nextPlayer();
                }

                pl1.setText("P1: " + p1Score);
                pl2.setText("P2: " + score);
                round += points;
                roundNum.setText("Round: " + round);
            }
        });

        hold = (Button)findViewById(R.id.hold);
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                boolean win = isWinner();

                if(win == false){
                    nextPlayer();
                }
            }
        });

        die1 = (FrameLayout)findViewById(R.id.die1);
        die2 = (FrameLayout)findViewById(R.id.die2);
    }

    public void nextPlayer(){
        score += round;

        Intent intent2 = new Intent(Player2.this, MainActivity.class);
        intent2.putExtra("p1Score", p1Score);
        intent2.putExtra("p2Score", score);
        intent2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent2);
    }

    public boolean isWinner() {
        boolean win = false;

        if(p1Score >= 100){
            displayWinner("Player 1");
            win = true;
        }

        if(score >= 100) {
            displayWinner("Player 2");
            win = true;
        }

        return win;
    }

    public void displayWinner(String player){
        AlertDialog alertDialog = new AlertDialog.Builder(Player2.this).create();
        alertDialog.setTitle(player);
        alertDialog.setMessage("Wins!");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    // get two random ints between 1 and 6 inclusive
    public void rollDice(){
        val1 = 1 + (int)(6 * Math.random());
        val2 = 1 + (int)(6 * Math.random());
        setDie(val1, die1);
        setDie(val2, die2);
        points = val1 + val2;
    }

    // set the appropriate picture for each die per int
    public void setDie(int value, FrameLayout layout){
        Drawable pic = null;

        switch(value){
            case 1:
                pic = getResources().getDrawable(R.drawable.die_face_1);
                break;
            case 2:
                pic = getResources().getDrawable(R.drawable.die_face_2);
                break;
            case 3:
                pic = getResources().getDrawable(R.drawable.die_face_3);
                break;
            case 4:
                pic = getResources().getDrawable(R.drawable.die_face_4);
                break;
            case 5:
                pic = getResources().getDrawable(R.drawable.die_face_5);
                break;
            case 6:
                pic = getResources().getDrawable(R.drawable.die_face_6);
                break;
        }

        layout.setBackground(pic);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
