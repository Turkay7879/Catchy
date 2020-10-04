package com.homedev.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    int countdown;
    Intent intent;
    String game_difficulty;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        countdown = 20;
        intent = new Intent(MainActivity2.this, MainActivity3.class);
        sharedPreferences = getApplicationContext().getSharedPreferences("com.homedev.catchthekenny", MODE_PRIVATE);
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
        Intent intent1 = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(intent1);
    }

    public void quit(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity2.this);
        alert.setTitle("Quit the game");
        alert.setMessage("Are you sure you want to quit the game now?");

        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }
}