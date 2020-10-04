package com.homedev.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    TextView textView;
    int countdown, time;
    int score;
    String difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        score = 0;
        Intent intent = getIntent();
        countdown = intent.getIntExtra("time", 0);
        if (countdown != 0)
            time = countdown * 1000;

        CountDownTimer timer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Set textView text for time
            }

            @Override
            public void onFinish() {
                AlertDialog.Builder restart = new AlertDialog.Builder(MainActivity3.this);
                restart.setTitle("Game over");
                restart.setMessage("Your total score is: " + score + ". Would you like to try again?");

                restart.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent1 = new Intent(MainActivity3.this, MainActivity.class);
                        intent1.putExtra("score", score);
                        intent1.putExtra("difficulty", difficulty);
                        startActivity(intent1);
                    }
                });

                restart.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        score = 0;
                        
                    }
                });
            }
        };
    }
}