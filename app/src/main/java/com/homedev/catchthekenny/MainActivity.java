package com.homedev.catchthekenny;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    int hs_easy, hs_medium, hs_hard;

    TextView textView1, textView2, textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView_highscoreeasy);
        textView2 = findViewById(R.id.textView_highscoremedium);
        textView3 = findViewById(R.id.textView_highscorehard);

        sharedPreferences = getApplicationContext().getSharedPreferences("com.homedev.catchthekenny", Context.MODE_PRIVATE);

        int temp_hs_easy = sharedPreferences.getInt("score_easy", 0);
        int temp_hs_medium = sharedPreferences.getInt("score_medium", 0);
        int temp_hs_hard = sharedPreferences.getInt("score_hard", 0);

        String mode = sharedPreferences.getString("last_difficulty", "");
        int score = sharedPreferences.getInt("last_score", 0);

        if (!mode.equals("")) {
            if (mode.equals("Easy")) check_Score(mode, score, temp_hs_easy);
            else if (mode.equals("Medium")) check_Score(mode, score, temp_hs_medium);
            else check_Score(mode, score, temp_hs_hard);
        }
    }

    public void start(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }

    private void check_Score(String mode, int last_score, int temp_hs) {
        if (mode.equals("Easy")) {
            if (last_score > temp_hs) hs_easy = last_score;
            else hs_easy = temp_hs;
            sharedPreferences.edit().putInt("score_easy", hs_easy).apply();
            textView1.setText("High Score (Easy): " + sharedPreferences.getInt("score_easy", 0));
        }

        else if (mode.equals("Medium")) {
            if (last_score > temp_hs) hs_medium = last_score;
            else hs_medium = temp_hs;
            sharedPreferences.edit().putInt("score_medium", hs_medium).apply();
            textView2.setText("High Score (Medium): " + sharedPreferences.getInt("score_medium", 0));
        }

        else {
            if (last_score > temp_hs) hs_hard = last_score;
            else hs_hard = temp_hs;
            sharedPreferences.edit().putInt("score_hard", hs_hard).apply();
            textView3.setText("High Score (Hard): " + sharedPreferences.getInt("score_hard", 0));
        }
    }

    public void quit(View view) {
        finishAffinity();
    }
}