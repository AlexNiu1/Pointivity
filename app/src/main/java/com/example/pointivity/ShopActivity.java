package com.example.pointivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ShopActivity extends AppCompatActivity {
    Button button5, button3;
    ConstraintLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_shop);
        //change colors
        button5 = findViewById(R.id.button5);
        button3 = findViewById(R.id.button3);
        layout = findViewById(R.id.linearlayout);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //change color to red
                layout.setBackgroundColor(Color.RED);

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //change color to red
                layout.setBackgroundColor(Color.BLUE);

            }
        });
        SharedPreferences prefs = getSharedPreferences("app", MODE_PRIVATE);
        int points = prefs.getInt("points", 0);
        TextView point = (TextView) findViewById(R.id.points3);
        point.setText("Points: " + points);
        TextView homebutton = findViewById(R.id.homebutton);
        homebutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                // start the game
                startActivity(new Intent(ShopActivity.this, MainActivity.class));
            }
        });
    }

}
