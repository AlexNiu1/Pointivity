package com.example.pointivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.content.SharedPreferences;

public class MainActivity extends AppCompatActivity {

    protected static int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("app", MODE_PRIVATE);
        points = prefs.getInt("points", 0);
        // Not 100% sure but try this when u want to edit points:
        /*
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("points", (int) points + earnedPoints);
        editor.apply();
         */

        setContentView(R.layout.activity_main);
        //test
        // making the MainActivity full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // listen for profile button
        findViewById(R.id.profile).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                // start the game
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });

        // listen for shop button
        findViewById(R.id.shop).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                // start the game
                startActivity(new Intent(MainActivity.this, ShopActivity.class));
            }
        });
    }
}