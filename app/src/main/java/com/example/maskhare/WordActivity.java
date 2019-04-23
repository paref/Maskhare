package com.example.maskhare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.maskhare.Models.Category;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class WordActivity extends AppCompatActivity {
    int Round;
    int Duration;
    int Level = 1;
    int Category_Id = 0;
    List<Category> categories;
    List<String> Names;
    int Count;
    int PlayersCount;
    int RoundCounter;
    static boolean io;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        DBService service = new DBService(this);
        categories = service.getCategories();
        Names = new ArrayList<>();
        for (Category category : categories
        ) {
            Names.add(category.getTitle());
        }
        Intent intent = getIntent();
        Round = intent.getIntExtra("Round", 0);
        Duration = intent.getIntExtra("Duration", 0);
        Count = intent.getIntExtra("Count", 0);
        PlayersCount = intent.getIntExtra("PlayersCount", 0);
        int score = intent.getIntExtra("Score", 0);
        if (io) {
            writeToFile("," + score);
        }
        RoundCounter = intent.getIntExtra("RoundCounter", 0);
        if (Count == PlayersCount) {
            Count = 0;
            RoundCounter++;
            writeToFile("\r\n");
        }
        if (RoundCounter == Round) {
            Finish();
        }
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
                io = true;
                intent.putExtra("Round", Round);
                intent.putExtra("Duration", Duration);
                intent.putExtra("Level", Level);
                intent.putExtra("Category_Id", Category_Id);
                intent.putExtra("PlayersCount", PlayersCount);
                intent.putExtra("Count", Count);
                intent.putExtra("RoundCounter", RoundCounter);
                startActivity(intent);
            }
        });
        Spinner CategorySpinner = findViewById(R.id.CategorySpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, Names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CategorySpinner.setAdapter(adapter);
        CategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Category_Id = pos + 1;
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        TextView TeamCountLabel = findViewById(R.id.TeamCountLabel);
        TeamCountLabel.setText("Team " + String.valueOf(Count + 1));
        TextView RoundCounterLabel = findViewById(R.id.RoundCounterLabel);
        RoundCounterLabel.setText("Round " + String.valueOf(RoundCounter + 1));
    }

    private void Finish() {
        Intent intent = new Intent(WordActivity.this, ResultActivity.class);
        startActivity(intent);
        finish();
    }

    private void writeToFile(String content) {
        try {
            File file = new File(Environment.getExternalStorageDirectory() + "/test.txt");

            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            writer.append(content);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            Log.e("wtf",  e.getMessage());
        }
    }
}
