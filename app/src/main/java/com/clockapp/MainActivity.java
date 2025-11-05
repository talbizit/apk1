package com.clockapp;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView clockTextView;
    private LinearLayout mainLayout;
    private Handler handler;
    private Runnable updateTimeRunnable;
    private int currentBackgroundColor = Color.WHITE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clockTextView = findViewById(R.id.clockTextView);
        mainLayout = findViewById(R.id.mainLayout);

        Button btnRed = findViewById(R.id.btnRed);
        Button btnGreen = findViewById(R.id.btnGreen);
        Button btnBlue = findViewById(R.id.btnBlue);
        Button btnYellow = findViewById(R.id.btnYellow);
        Button btnPurple = findViewById(R.id.btnPurple);
        Button btnWhite = findViewById(R.id.btnWhite);

        handler = new Handler();
        updateTimeRunnable = new Runnable() {
            @Override
            public void run() {
                updateTime();
                handler.postDelayed(this, 1000);
            }
        };

        btnRed.setOnClickListener(v -> changeBackgroundColor(Color.parseColor("#FFCDD2")));
        btnGreen.setOnClickListener(v -> changeBackgroundColor(Color.parseColor("#C8E6C9")));
        btnBlue.setOnClickListener(v -> changeBackgroundColor(Color.parseColor("#BBDEFB")));
        btnYellow.setOnClickListener(v -> changeBackgroundColor(Color.parseColor("#FFF9C4")));
        btnPurple.setOnClickListener(v -> changeBackgroundColor(Color.parseColor("#E1BEE7")));
        btnWhite.setOnClickListener(v -> changeBackgroundColor(Color.WHITE));
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.post(updateTimeRunnable);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(updateTimeRunnable);
    }

    private void updateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String currentTime = sdf.format(new Date());
        clockTextView.setText(currentTime);
    }

    private void changeBackgroundColor(int color) {
        currentBackgroundColor = color;
        mainLayout.setBackgroundColor(color);
    }
}
