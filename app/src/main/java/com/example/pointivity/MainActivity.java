package com.example.pointivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        TextView mTextField = (TextView) findViewById(R.id.infotext);
        EditText mMinutes = (EditText) findViewById(R.id.minutes);
        Button mButton = (Button) findViewById(R.id.button);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mButton.setEnabled(false);
                mButton.setClickable(false);
                mMinutes.setEnabled(false);
                int start;
                if (mMinutes.getText().toString().isEmpty()){
                    start = 5;
                }
                else {
                    start = Integer.parseInt(mMinutes.getText().toString());
                }
                if (start < 5){
                    start = 5;
                }

                CountDownTimer timer = new CountDownTimer(start * 60000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        mTextField.setText("Time remaining:" + millisUntilFinished / 60000 + " minutes, " + (millisUntilFinished % 60000)/1000 + "seconds");
                    }

                    public void onFinish() {
                        mTextField.setText("Focus Mode Complete, congratulations");
                        mMinutes.setText("");
                        mButton.setEnabled(true);
                        mButton.setClickable(true);
                        mMinutes.setEnabled(true);
                    }
                };
                timer.start();


            }
        });
    }
}