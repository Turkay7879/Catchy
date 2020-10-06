package com.homedev.catchy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity4 extends AppCompatActivity {
    int countdown, time;
    int score, hs_easy, hs_medium, hs_hard;
    int delay;
    String difficulty, player_character;

    TextView textView, textView2;
    ImageView[] character, bomb;
    GridLayout layout1, layout2, layout3, layout4;

    SharedPreferences sharedPreferences;
    Handler handler;
    Runnable runnable;
    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        random = new Random();

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        sharedPreferences = getApplicationContext().getSharedPreferences("com.homedev.catchy", Context.MODE_PRIVATE);
        score = 0;
        player_character = sharedPreferences.getString("character", "");
        difficulty = sharedPreferences.getString("last_difficulty", "");

        textView = findViewById(R.id.textView_time);
        textView2 = findViewById(R.id.textView_Score);

        layout1 = findViewById(R.id.grid_layout_charactermain);
        layout2 = findViewById(R.id.grid_layout_anna);
        layout3 = findViewById(R.id.grid_layout_olaf);
        layout4 = findViewById(R.id.grid_layout_obstacle);
        if (!difficulty.equals("Hard")) layout4.setVisibility(View.INVISIBLE);
        layout2.setVisibility(View.INVISIBLE); layout3.setVisibility(View.INVISIBLE);

        character = new ImageView[12]; bomb = new ImageView[12];
        fill_image_arrays();

        Intent intent = getIntent();
        countdown = intent.getIntExtra("time", 0);
        time = countdown * 1000;

        if (difficulty.equals("Easy") || difficulty.equals("Medium")) delay = 750;
        else delay = 500;

        hide_image();

        new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int current_time = (int) millisUntilFinished / 1000;
                if (current_time <= 5) textView.setTextColor(Color.RED);
                textView.setText("Kalan Süre: " + current_time);

                if (current_time % 10 == 0 && difficulty.equals("Easy")) delay -= 100;
                else if (current_time % 8 == 0 && difficulty.equals("Medium")) delay -= 50;
                else if (current_time % 10 == 0 && difficulty.equals("Hard")) delay -= 50;
            }

            @Override
            public void onFinish() {
                handler.removeCallbacks(runnable);
                for (ImageView image : character) image.setVisibility(View.INVISIBLE);
                if (difficulty.equals("Hard")) for (ImageView image : bomb) image.setVisibility(View.INVISIBLE);

                update_high_scores();

                AlertDialog.Builder restart = new AlertDialog.Builder(MainActivity4.this);
                restart.setCancelable(false);
                restart.setTitle("Oyun Bitti");
                restart.setMessage("Toplam skorun: " + score + ". Tekrar denemek ister misin?");

                restart.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sharedPreferences.edit().putInt("last_score", score).apply();

                        Intent intent1 = new Intent(MainActivity4.this, MainActivity.class);
                        startActivity(intent1);
                    }
                });

                restart.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        score = 0;
                        Intent intent2 = new Intent(MainActivity4.this, MainActivity2.class);
                        startActivity(intent2);
                    }
                });
                restart.show();
            }
        }.start();
    }

    public void increase_score(View view) {
        score++;
        textView2.setText("Skor: " + score);
    }

    public void decrease_score(View view) {
        score--;
        textView2.setText("Skor: " + score);
    }

    private void hide_image() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : character) image.setVisibility(View.INVISIBLE);
                if (difficulty.equals("Hard")) for (ImageView image : bomb) image.setVisibility(View.INVISIBLE);
                int rnd1, rnd2 = random.nextInt(12);
                if (difficulty.equals("Hard")) {
                    rnd1 = random.nextInt(101);
                    if (rnd1 > 90) bomb[rnd2].setVisibility(View.VISIBLE);
                    else character[rnd2].setVisibility(View.VISIBLE);
                }
                else character[rnd2].setVisibility(View.VISIBLE);

                handler.postDelayed(this,delay);
            }
        };

        handler.post(runnable);
    }

    private void update_high_scores() {
        if (difficulty.equals("Easy")) {
            hs_easy = sharedPreferences.getInt("score_easy", 0);
            if (score > hs_easy) {
                hs_easy = score;
                sharedPreferences.edit().putInt("score_easy", hs_easy).apply();
                Toast.makeText(MainActivity4.this, "Yeni bir yüksek skor!", Toast.LENGTH_LONG).show();
            }
        }

        else if (difficulty.equals("Medium")) {
            hs_medium = sharedPreferences.getInt("score_medium", 0);
            if (score > hs_medium) {
                hs_medium = score;
                sharedPreferences.edit().putInt("score_medium", hs_medium).apply();
                Toast.makeText(MainActivity4.this, "Yeni bir yüksek skor!", Toast.LENGTH_LONG).show();
            }
        }

        else {
            hs_hard = sharedPreferences.getInt("score_hard", 0);
            if (score > hs_hard) {
                hs_hard = score;
                sharedPreferences.edit().putInt("score_hard", hs_hard).apply();
                Toast.makeText(MainActivity4.this, "Yeni bir yüksek skor!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void fill_image_arrays() {
        character[0] = findViewById(R.id.elsa0); character[1] = findViewById(R.id.elsa1); character[2] = findViewById(R.id.elsa2);
        character[3] = findViewById(R.id.elsa3); character[4] = findViewById(R.id.elsa4); character[5] = findViewById(R.id.elsa5);
        character[6] = findViewById(R.id.elsa6); character[7] = findViewById(R.id.elsa7); character[8] = findViewById(R.id.elsa8);
        character[9] = findViewById(R.id.elsa9); character[10] = findViewById(R.id.elsa10); character[11] = findViewById(R.id.elsa11);

        if (player_character.equals("Anna")) for (ImageView imageView : character) imageView.setImageResource(R.drawable.anna);
        else if (player_character.equals("Olaf")) for (ImageView imageView : character) imageView.setImageResource(R.drawable.olaf);

        if (difficulty.equals("Hard")) {
            bomb[0] = findViewById(R.id.bomb0); bomb[1] = findViewById(R.id.bomb1); bomb[2] = findViewById(R.id.bomb2);
            bomb[3] = findViewById(R.id.bomb3); bomb[4] = findViewById(R.id.bomb4); bomb[5] = findViewById(R.id.bomb5);
            bomb[6] = findViewById(R.id.bomb6); bomb[7] = findViewById(R.id.bomb7); bomb[8] = findViewById(R.id.bomb8);
            bomb[9] = findViewById(R.id.bomb9); bomb[10] = findViewById(R.id.bomb10); bomb[11] = findViewById(R.id.bomb11);
        }
    }
}