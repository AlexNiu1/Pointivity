package com.example.pointivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected static int points;
    protected static int totaltime;
    protected static int besttime;
    protected static int backgroundcolor;
    CountDownTimer timer;
    TextView mTextField;
    EditText mMinutes;
    Button mButton;
    ImageView profilebutton;
    ImageView shopbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("app", MODE_PRIVATE);
        points = prefs.getInt("points", 0);
        totaltime = prefs.getInt("totaltime", 0);
        besttime = prefs.getInt("besttime", 0);
        //0 is white 1 is red 2 is blue 3 is green 4 is random
        backgroundcolor = prefs.getInt("background", 0);
        setContentView(R.layout.activity_main);
        //test
        // making the MainActivity full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ConstraintLayout layout = findViewById(R.id.layout);
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
        // listen for profile button
        profilebutton = findViewById(R.id.profile);
        TextView point = (TextView) findViewById(R.id.points);
        point.setText("Points: " + this.points);
        shopbutton = findViewById(R.id.shop);
        profilebutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                // start the game
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });

        // listen for shop button
        shopbutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                // start the game
                startActivity(new Intent(MainActivity.this, ShopActivity.class));
            }
        });
        mTextField = (TextView) findViewById(R.id.infotext);
        mMinutes = (EditText) findViewById(R.id.minutes);
        mButton = (Button) findViewById(R.id.button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mButton.setEnabled(false);
                mButton.setClickable(false);
                mMinutes.setEnabled(false);
                mMinutes.setClickable(false);
                profilebutton.setClickable(false);
                profilebutton.setEnabled(false);
                shopbutton.setClickable(false);
                shopbutton.setEnabled(false);
                int start;
                if (mMinutes.getText().toString().isEmpty()){
                    start = 1;
                }
                else {
                    start = Integer.parseInt(mMinutes.getText().toString());
                }
                if (start < 1){
                    start = 1;
                }
                final int pointsearned = start*60;

                timer = new CountDownTimer(start * 60000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        String countdown = "Time remaining: " + millisUntilFinished / 60000;
                        if (millisUntilFinished >= 60000 && millisUntilFinished < 120000) {
                            countdown = countdown + " minute, ";
                        }
                        else{
                            countdown = countdown + " minutes, ";
                        }
                        countdown = countdown + (millisUntilFinished % 60000)/1000;
                        if ((millisUntilFinished % 60000)/1000 == 1){
                            countdown = countdown + " second";
                        }
                        else{
                            countdown = countdown + " seconds";
                        }
                        mTextField.setText(countdown);
                    }

                    public void onFinish() {
                        mTextField.setText("Focus Mode Complete, congratulations. Points earned: " + pointsearned);
                        mMinutes.setText("");
                        mButton.setEnabled(true);
                        mButton.setClickable(true);
                        mMinutes.setEnabled(true);
                        mMinutes.setClickable(true);
                        profilebutton.setClickable(true);
                        profilebutton.setEnabled(true);
                        shopbutton.setClickable(true);
                        shopbutton.setEnabled(true);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putInt("points", (int) points + pointsearned);
                        editor.putInt("besttime", Math.max(besttime, pointsearned));
                        editor.putInt("totaltime", totaltime + pointsearned);
                        editor.apply();
                        points = prefs.getInt("points", 0);
                        totaltime = prefs.getInt("totaltime", 0);
                        besttime = prefs.getInt("besttime", 0);
                        point.setText("Points: " + points);
                        timer = null;
                    }
                };
                timer.start();
            }
        });
    }
    @Override
    public void onUserLeaveHint() {
        if (timer != null){
            timer.cancel();
            mTextField.setText("Focus mode failed as you left the app, no points earned");
            mMinutes.setText("");
            mButton.setEnabled(true);
            mButton.setClickable(true);
            mMinutes.setEnabled(true);
            mMinutes.setClickable(true);
            profilebutton.setClickable(true);
            profilebutton.setEnabled(true);
            shopbutton.setClickable(true);
            shopbutton.setEnabled(true);
        }
    }
}