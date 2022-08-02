package com.example.pointivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ShopActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_shop);
        SharedPreferences prefs = getSharedPreferences("app", MODE_PRIVATE);
        ConstraintLayout layout = findViewById(R.id.layout);
        int backgroundcolor = prefs.getInt("background", 0);
        int rand = backgroundcolor;
        if (rand == 4){
            rand = (int) Math.floor(Math.random()*4);
        }
        if (rand == 0){
            layout.setBackgroundColor(Color.WHITE);
        }
        if (rand == 1){
            layout.setBackgroundColor(Color.RED);
        }
        if (rand == 2){
            layout.setBackgroundColor(Color.BLUE);
        }
        if (rand == 3) {
            layout.setBackgroundColor(Color.GREEN);
        }
        int points = prefs.getInt("points", 0);
        TextView point = (TextView) findViewById(R.id.points3);
        point.setText("Points: " + points);
        //change colors
        Button red = findViewById(R.id.red);
        Button blue = findViewById(R.id.blue);
        Button green = findViewById(R.id.green);
        Button white = findViewById(R.id.white);
        Button random = findViewById(R.id.random);
        red.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("background", 1);
                editor.apply();
                layout.setBackgroundColor(Color.RED);
            }
        });
        blue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("background", 2);
                editor.apply();
                layout.setBackgroundColor(Color.BLUE);
            }
        });
        green.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("background", 3);
                editor.apply();
                layout.setBackgroundColor(Color.GREEN);
            }
        });
        white.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("background", 0);
                editor.apply();
                layout.setBackgroundColor(Color.WHITE);
            }
        });
        random.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("background", 4);
                editor.apply();
                int rand = (int) Math.floor(Math.random()*4);
                if (rand == 0){
                    layout.setBackgroundColor(Color.WHITE);
                }
                if (rand == 1){
                    layout.setBackgroundColor(Color.RED);
                }
                if (rand == 2){
                    layout.setBackgroundColor(Color.BLUE);
                }
                if (rand == 3) {
                    layout.setBackgroundColor(Color.GREEN);
                }
            }
        });
        TextView homebutton = findViewById(R.id.homebutton);
        homebutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                // start the game
                startActivity(new Intent(ShopActivity.this, MainActivity.class));
            }
        });
    }

}
