package com.homedev.catchy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView textView1, textView2, textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        textView1 = findViewById(R.id.textView_highscoreeasy);
        textView2 = findViewById(R.id.textView_highscoremedium);
        textView3 = findViewById(R.id.textView_highscorehard);

        sharedPreferences = getApplicationContext().getSharedPreferences("com.homedev.catchy", MODE_PRIVATE);

        textView1.setText("Yüksek Skor (Kolay): " + sharedPreferences.getInt("score_easy", 0));
        textView2.setText("Yüksek Skor (Orta): " + sharedPreferences.getInt("score_medium", 0));
        textView3.setText("Yüksek Skor (Zor): " + sharedPreferences.getInt("score_hard", 0));
    }

    public void start(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }

    public void quit(View view) {
        finishAffinity();
    }
}