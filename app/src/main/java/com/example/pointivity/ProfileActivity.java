package com.example.pointivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);
        SharedPreferences prefs = getSharedPreferences("app", MODE_PRIVATE);
        int points = prefs.getInt("points", 0);
    }

}
