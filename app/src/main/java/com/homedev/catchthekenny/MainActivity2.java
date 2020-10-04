package com.homedev.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    int countdown;
    Intent intent;
    String game_difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        countdown = 20;
        intent = new Intent(MainActivity2.this, MainActivity3.class);
    }

    public void level_easy(View view) {
        game_difficulty = "Easy";

        intent.putExtra("time", countdown);
        intent.putExtra("last_difficulty", game_difficulty);
        startActivity(intent);
    }

    public void level_medium(View view) {
        game_difficulty = "Medium";
        countdown = 40;

        intent.putExtra("time", countdown);
        intent.putExtra("last_difficulty", game_difficulty);
        startActivity(intent);
    }

    public void level_hard(View view) {
        game_difficulty = "Hard";
        countdown = 60;

        intent.putExtra("time", countdown);
        intent.putExtra("last_difficulty", game_difficulty);
        startActivity(intent);
    }
}