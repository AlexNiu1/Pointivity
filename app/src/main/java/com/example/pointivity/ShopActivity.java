package com.example.pointivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_shop);
        SharedPreferences prefs = getSharedPreferences("app", MODE_PRIVATE);
        int points = prefs.getInt("points", 0);
        // To edit the number of points, use this code:
        /*
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("points", (int) points - itemCost);
        editor.apply();
         */
        TextView homebutton = findViewById(R.id.homebutton);
        homebutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                // start the game
                startActivity(new Intent(ShopActivity.this, MainActivity.class));
            }
        });
    }

}
