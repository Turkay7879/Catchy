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
    int time;
    Intent intent;
    String difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        time = 20;
        intent = new Intent(MainActivity2.this, MainActivity3.class);
    }

    public void level_easy(View view) {
        difficulty = "Easy";

        intent.putExtra("time", time);
        intent.putExtra("difficulty", difficulty);
        startActivity(intent);
    }

    public void level_medium(View view) {
        difficulty = "Medium";
        time = 40;

        intent.putExtra("time", time);
        intent.putExtra("difficulty", difficulty);
        startActivity(intent);
    }

    public void level_hard(View view) {
        difficulty = "Hard";
        time = 60;

        intent.putExtra("time", time);
        intent.putExtra("difficulty", difficulty);
        startActivity(intent);
    }
}