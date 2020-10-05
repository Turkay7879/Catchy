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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity3 extends AppCompatActivity {
    int countdown;
    String game_difficulty, player_character;

    Intent intent;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


        intent = new Intent(MainActivity3.this, MainActivity4.class);
        sharedPreferences = getApplicationContext().getSharedPreferences("com.homedev.catchy", MODE_PRIVATE);

        countdown = 20;
        player_character = sharedPreferences.getString("character", "");
        Toast.makeText(this, player_character + " seçildi!", Toast.LENGTH_SHORT).show();
    }

    public void level_easy(View view) {
        game_difficulty = "Easy";
        sharedPreferences.edit().putString("last_difficulty", game_difficulty).apply();

        intent.putExtra("time", countdown);
        startActivity(intent);
    }

    public void level_medium(View view) {
        game_difficulty = "Medium";
        sharedPreferences.edit().putString("last_difficulty", game_difficulty).apply();
        countdown = 40;

        intent.putExtra("time", countdown);
        startActivity(intent);
    }

    public void level_hard(View view) {
        game_difficulty = "Hard";
        sharedPreferences.edit().putString("last_difficulty", game_difficulty).apply();
        countdown = 60;

        intent.putExtra("time", countdown);
        startActivity(intent);
    }

    public void go_back(View view) {
        Intent intent1 = new Intent(MainActivity3.this, MainActivity2.class);
        startActivity(intent1);
    }

    public void quit(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity3.this);
        alert.setTitle("Oyundan Çık");
        alert.setMessage("Oyundan şimdi çıkmak istediğine emin misin?");

        alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });

        alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }
}