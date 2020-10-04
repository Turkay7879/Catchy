package com.homedev.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity3 extends AppCompatActivity {
    TextView textView, textView2;
    ImageView[] kenny, bomb;
    int countdown, time;
    int score;
    int delay;
    String difficulty;

    SharedPreferences sharedPreferences;
    Handler handler;
    Runnable runnable;
    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        random = new Random();

        textView = findViewById(R.id.textView_time);
        textView2 = findViewById(R.id.textView_Score);

        kenny = new ImageView[12]; bomb = new ImageView[12];
        kenny[0] = findViewById(R.id.kenny0); kenny[1] = findViewById(R.id.kenny1); kenny[2] = findViewById(R.id.kenny2);
        kenny[3] = findViewById(R.id.kenny3); kenny[4] = findViewById(R.id.kenny4); kenny[5] = findViewById(R.id.kenny5);
        kenny[6] = findViewById(R.id.kenny6); kenny[7] = findViewById(R.id.kenny7); kenny[8] = findViewById(R.id.kenny8);
        kenny[9] = findViewById(R.id.kenny9); kenny[10] = findViewById(R.id.kenny10); kenny[11] = findViewById(R.id.kenny11);

        bomb[0] = findViewById(R.id.bomb0); bomb[1] = findViewById(R.id.bomb1); bomb[2] = findViewById(R.id.bomb2);
        bomb[3] = findViewById(R.id.bomb3); bomb[4] = findViewById(R.id.bomb4); bomb[5] = findViewById(R.id.bomb5);
        bomb[6] = findViewById(R.id.bomb6); bomb[7] = findViewById(R.id.bomb7); bomb[8] = findViewById(R.id.bomb8);
        bomb[9] = findViewById(R.id.bomb9); bomb[10] = findViewById(R.id.bomb10); bomb[11] = findViewById(R.id.bomb11);

        sharedPreferences = getApplicationContext().getSharedPreferences("com.homedev.catchthekenny", Context.MODE_PRIVATE);
        score = 0;

        Intent intent = getIntent();
        countdown = intent.getIntExtra("time", 0);
        time = countdown * 1000;

        difficulty = sharedPreferences.getString("last_difficulty", "");
        if (difficulty.equals("Easy") || difficulty.equals("Medium")) delay = 750;
        else delay = 500;

        hide_image();

        new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int current_time = (int) millisUntilFinished / 1000;
                textView.setText("Time: " + current_time);

                if (current_time % 10 == 0 && difficulty.equals("Easy")) delay -= 100;
                else if (current_time % 8 == 0 && difficulty.equals("Medium")) delay -= 50;
                else if (current_time % 7 == 0 && difficulty.equals("Hard")) delay -= 50;
            }

            @Override
            public void onFinish() {
                handler.removeCallbacks(runnable);
                for (ImageView image : kenny) image.setVisibility(View.INVISIBLE);

                AlertDialog.Builder restart = new AlertDialog.Builder(MainActivity3.this);
                restart.setTitle("Game over");
                restart.setMessage("Your total score is: " + score + ". Would you like to try again?");

                restart.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sharedPreferences.edit().putInt("last_score", score).apply();

                        Intent intent1 = new Intent(MainActivity3.this, MainActivity.class);
                        startActivity(intent1);
                    }
                });

                restart.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        score = 0;
                        Intent intent2 = new Intent(MainActivity3.this, MainActivity2.class);
                        startActivity(intent2);
                    }
                });
                restart.show();
            }
        }.start();
    }

    public void increase_score(View view) {
        score++;
        textView2.setText("Score: " + score);
    }

    public void decrease_score(View view) {
        score--;
        textView2.setText("Score: " + score);
    }

    private void hide_image() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : kenny) image.setVisibility(View.INVISIBLE);
                for (ImageView image : bomb) image.setVisibility(View.INVISIBLE);
                int rnd1, rnd2 = random.nextInt(12);
                if (difficulty.equals("Hard")) {
                    rnd1 = random.nextInt(101);
                    if (rnd1 > 80) bomb[rnd2].setVisibility(View.VISIBLE);
                    else kenny[rnd2].setVisibility(View.VISIBLE);
                }
                else kenny[rnd2].setVisibility(View.VISIBLE);

                handler.postDelayed(this,delay);
            }
        };

        handler.post(runnable);
    }

}