package com.homedev.catchy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {
    String player_character;
    AlertDialog.Builder alert;
    SharedPreferences sharedPreferences;
    Intent intent;

    int id_elsa, id_anna, id_olaf;

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

        player_character = "";
        id_elsa = R.id.image1; id_anna = R.id.image2; id_olaf = R.id.image3;
        sharedPreferences = getApplicationContext().getSharedPreferences("com.homedev.catchy", MODE_PRIVATE);

        alert = new AlertDialog.Builder(MainActivity2.this);
        alert.setTitle("Karakteri Onayla");
    }

    public void choose(View view) {
        final int id = view.getId();

        if (id == id_anna) player_character = "Anna";
        else if (id == id_elsa) player_character = "Elsa";
        else player_character = "Olaf";

        alert.setMessage(player_character + " ile oynamak istediğine emin misin?");
        alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sharedPreferences.edit().putString("character", player_character).apply();
                intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
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