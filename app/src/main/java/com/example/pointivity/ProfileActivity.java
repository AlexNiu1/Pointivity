package com.example.pointivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);
        SharedPreferences prefs = getSharedPreferences("app", MODE_PRIVATE);
        ConstraintLayout layout = findViewById(R.id.layout2);
        int backgroundcolor = prefs.getInt("background", Color.WHITE);
        int rand = backgroundcolor;
        if (rand == 0) {
            rand = (int) Math.floor(Math.random() * 4);
            layout.setBackgroundColor(MainActivity.colors[rand + 1]);
        } else {
            layout.setBackgroundColor(backgroundcolor);
        }
        int points = prefs.getInt("points", 0);
        TextView point = (TextView) findViewById(R.id.points2);
        point.setText("Points: " + points);
        int besttime = prefs.getInt("besttime", 0);
        String countdown = "Longest Focus Mode Time: " + besttime / 60;
        if (besttime >= 60 && besttime < 120) {
            countdown = countdown + " minute, ";
        }
        else{
            countdown = countdown + " minutes, ";
        }
        countdown = countdown + besttime % 60;
        if (besttime % 60 == 1){
            countdown = countdown + " second";
        }
        else{
            countdown = countdown + " seconds";
        }
        TextView best = (TextView) findViewById(R.id.best);
        best.setText(countdown);
        int totaltime = prefs.getInt("totaltime", 0);
        countdown = "Total Focus Mode Time: " + totaltime / 60;
        if (totaltime >= 60 && totaltime < 120) {
            countdown = countdown + " minute, ";
        }
        else{
            countdown = countdown + " minutes, ";
        }
        countdown = countdown + totaltime % 60;
        if (totaltime % 60 == 1){
            countdown = countdown + " second";
        }
        else{
            countdown = countdown + " seconds";
        }
        TextView total = (TextView) findViewById(R.id.total);
        total.setText(countdown);
        TextView homebutton = findViewById(R.id.homebutton2);
        homebutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                // start the game
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });
    }

}
