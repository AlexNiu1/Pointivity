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
import androidx.core.content.ContextCompat;

import yuku.ambilwarna.AmbilWarnaDialog;

public class ShopActivity extends AppCompatActivity {

    private int defaultColor;
    private ConstraintLayout layout;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_shop);
        prefs = getSharedPreferences("app", MODE_PRIVATE);
        layout = findViewById(R.id.layout);
        int backgroundcolor = prefs.getInt("background", Color.WHITE);
        defaultColor = ContextCompat.getColor(ShopActivity.this, com.google.android.material.R.color.design_default_color_primary);
        int rand = backgroundcolor;
        if (rand == 0) {
            rand = (int) Math.floor(Math.random() * 4);
            layout.setBackgroundColor(MainActivity.colors[rand + 1]);
        } else {
            layout.setBackgroundColor(backgroundcolor);
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
        Button pick = findViewById(R.id.pick);
        int redpoints = 60;
        int bluepoints = 120;
        int greenpoints = 180;
        int randompoints = 240;
        int pickPoints = 360;
        TextView red2 = findViewById(R.id.red2);
        TextView blue2 = findViewById(R.id.blue2);
        TextView green2 = findViewById(R.id.green2);
        TextView random2 = findViewById(R.id.random2);
        TextView pick2 = findViewById(R.id.pick2);
        if (points < redpoints){
            red.setEnabled(false);
            red.setClickable(false);
            red2.setText("60 points to unlock");
        }
        if (points < bluepoints){
            blue.setEnabled(false);
            blue.setClickable(false);
            blue2.setText("120 points to unlock");
        }
        if (points < greenpoints){
            green.setEnabled(false);
            green.setClickable(false);
            green2.setText("180 points to unlock");
        }
        if (points < randompoints){
            random.setEnabled(false);
            random.setClickable(false);
            random2.setText("240 points to unlock");
        }
        if (points < pickPoints){
            pick.setEnabled(false);
            pick.setClickable(false);
            pick2.setText("360 points to unlock");
        }
        Button resetpoints = findViewById(R.id.resetpoints);
        red.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("background", Color.RED);
                editor.apply();
                layout.setBackgroundColor(Color.RED);
            }
        });
        blue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("background", Color.BLUE);
                editor.apply();
                layout.setBackgroundColor(Color.BLUE);
            }
        });
        green.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("background", Color.GREEN);
                editor.apply();
                layout.setBackgroundColor(Color.GREEN);
            }
        });
        white.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("background", Color.WHITE);
                editor.apply();
                layout.setBackgroundColor(Color.WHITE);
            }
        });
        random.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences.Editor editor = prefs.edit();
                int rand = (int) Math.floor(Math.random() * 4);
                layout.setBackgroundColor(MainActivity.colors[rand + 1]);
                editor.putInt("background", 0);
                editor.apply();
            }
        });
        pick.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openColorPicker();
            }
        });
        TextView homebutton = findViewById(R.id.homebutton);
        homebutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                // start the game
                startActivity(new Intent(ShopActivity.this, MainActivity.class));
            }
        });
        resetpoints.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("points", 0);
                editor.putInt("background", 0);
                editor.apply();
                startActivity(new Intent(ShopActivity.this, MainActivity.class));
            }
        });
    }

    public void openColorPicker() {
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(this, defaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                defaultColor = color;
                layout.setBackgroundColor(defaultColor);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("background", defaultColor);
                editor.apply();
            }
        });
        ambilWarnaDialog.show();
    }
}
