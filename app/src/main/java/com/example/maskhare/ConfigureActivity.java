package com.example.maskhare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class ConfigureActivity extends AppCompatActivity {
    int Round = 1;
    int Duration;
    int PlayersCount = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);
        SeekBar DurationSeekBar = findViewById(R.id.DurationSeekBar);
        final TextView DurationTextView = findViewById(R.id.DurationTextView);
        DurationSeekBar.setMax(300);
        DurationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Duration = progress;
                DurationTextView.setText(String.valueOf(progress) + " sec");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        final TextView RoundTextView = findViewById(R.id.RoundTextView);
        final SeekBar RoundSeekBar = findViewById(R.id.RoundSeekBar);
        RoundSeekBar.setMax(5);
        RoundSeekBar.setProgress(1);
        RoundSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (seekBar.getProgress() < 1) {
                    seekBar.setProgress(1);
                    progress = 1;
                }
                Round = progress;
                RoundTextView.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        Button button = findViewById(R.id.GoBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfigureActivity.this, WordActivity.class);
                intent.putExtra("Round", Round);
                intent.putExtra("Duration", Duration);
                intent.putExtra("PlayersCount", PlayersCount);
                startActivity(intent);
            }
        });
        final TextView PlayersCountTextView = findViewById(R.id.PlayersCountTextView);
        SeekBar PlayersCountSeekBar = findViewById(R.id.PlayersCountSeekBar);
        PlayersCountSeekBar.setMax(5);
        PlayersCountSeekBar.setProgress(2);
        PlayersCountSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (seekBar.getProgress() < 2) {
                    seekBar.setProgress(2);
                    progress = 2;
                }
                PlayersCountTextView.setText(String.valueOf(progress));
                PlayersCount = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
