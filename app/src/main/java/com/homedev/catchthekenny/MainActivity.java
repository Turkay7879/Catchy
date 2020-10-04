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
    TextView textView1, textView2, textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView_highscoreeasy);
        textView2 = findViewById(R.id.textView_highscoremedium);
        textView3 = findViewById(R.id.textView_highscorehard);

        sharedPreferences = getApplicationContext().getSharedPreferences("com.homedev.catchthekenny", Context.MODE_PRIVATE);

        textView1.setText("High Score (Easy): " + sharedPreferences.getInt("score_easy", 0));
        textView2.setText("High Score (Medium): " + sharedPreferences.getInt("score_medium", 0));
        textView3.setText("High Score (Hard): " + sharedPreferences.getInt("score_hard", 0));
    }

    public void start(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }

    public void quit(View view) {
        finishAffinity();
    }
}