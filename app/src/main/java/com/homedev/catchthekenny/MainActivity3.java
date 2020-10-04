package com.homedev.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    TextView textView;
    int countdown, time;
    int score;
    String difficulty;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        textView = findViewById(R.id.textView_time);
        sharedPreferences = getApplicationContext().getSharedPreferences("com.homedev.catchthekenny", Context.MODE_PRIVATE);
        score = 0;

        Intent intent = getIntent();
        countdown = intent.getIntExtra("time", 0);
        time = countdown * 1000;

        new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText("Time: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
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
}