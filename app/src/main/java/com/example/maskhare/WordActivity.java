package com.example.maskhare;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.maskhare.Models.Category;
import com.example.maskhare.Models.Thing;

import java.util.ArrayList;
import java.util.List;

public class WordActivity extends AppCompatActivity {
    int Round;
    int Duration;
    int Level = 1;
    int Category_Id = 0;
    List<Category> categories;
    int Count;
    int PlayersCount;
    int RoundCounter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        categories = new ArrayList<>();
        Intent intent = getIntent();
        Round = intent.getIntExtra("Round", 0);
        Duration = intent.getIntExtra("Duration", 0);
        Count = intent.getIntExtra("Count", 0);
        RoundCounter = intent.getIntExtra("RoundCounter", 0);
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
                switch (progress) {
                    case 1: {
                        LevelTextView.setText("Easy");
                        break;
                    }
                    case 2: {
                        LevelTextView.setText("Medium");
                        break;
                    }
                    case 3: {
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
                intent.putExtra("Category_Id", Category_Id);
                if (Count == PlayersCount) {
                    Count = 0;
                    RoundCounter++;
                }
                intent.putExtra("Count", Count);
                if (RoundCounter == Round) {
                    Finish();
                }
                intent.putExtra("RoundCounter", RoundCounter);
                startActivity(intent);
            }
        });
        Spinner CategorySpinner = findViewById(R.id.CategorySpinner);
        ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CategorySpinner.setAdapter(adapter);
        Category selectedItem = (Category) CategorySpinner.getSelectedItem();
        Category_Id = selectedItem.getId();
    }

    private void Finish() {

    }
}
