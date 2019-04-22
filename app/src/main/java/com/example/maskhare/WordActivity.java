package com.example.maskhare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class WordActivity extends AppCompatActivity {
    int Round;
    int Duration;
    int Level = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        Intent intent = getIntent();
        Round = intent.getIntExtra("Round", 0);
        Duration = intent.getIntExtra("Duration", 0);
        final TextView LevelTextView = findViewById(R.id.LevelTextView);
        final SeekBar LevelSeekBar = findViewById(R.id.LevelSeekBar);
        LevelSeekBar.setMax(3);
        LevelSeekBar.setProgress(1);
        LevelSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (seekBar.getProgress() < 1) {
                    seekBar.setProgress(1);
                    progress = 1;
                }
                switch (progress){
                    case 1:{
                        LevelTextView.setText("Easy");
                        break;
                    } case 2:{
                        LevelTextView.setText("Medium");
                        break;
                    } case 3:{
                        LevelTextView.setText("Hard");
                        break;
                    }
                }
                Level = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        Button GoBtn = findViewById(R.id.GoBtn);
        GoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WordActivity.this, PlayingActivity.class);
                intent.putExtra("Round", Round);
                intent.putExtra("Duration", Duration);
                intent.putExtra("Level", Level);
                startActivity(intent);
            }
        });
        Spinner CategorySpinner = findViewById(R.id.CategorySpinner);
    }
}
