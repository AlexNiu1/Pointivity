package com.example.pointivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }
}