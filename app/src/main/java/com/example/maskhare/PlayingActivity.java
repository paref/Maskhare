package com.example.maskhare;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.maskhare.Models.Category;
import com.example.maskhare.Models.Thing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayingActivity extends AppCompatActivity {
    int Round;
    int Duration;
    int Level;
    int Category_Id;
    DBService dbService;
    List<Category> categories;
    List<Thing> things;
    List<Thing> FilteredList;
    String WordText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);
        FilteredList = new ArrayList<>();
        dbService = new DBService(this);
        categories = dbService.getCategories();
        things = dbService.getThings();
        Intent intent = getIntent();
        Round = intent.getIntExtra("Round", 1);
        Duration = intent.getIntExtra("Duration", 1);
        Level = intent.getIntExtra("Level", 1);
        Category_Id = intent.getIntExtra("Category_Id", 0);
        for (Thing thing : things) {
            if (thing.getCategory_Id() == this.Category_Id) {
                FilteredList.add(thing);
            }
        }
        WordText = ChooseWord();
        Button GiveupBtn = findViewById(R.id.GiveupBtn);
        GiveupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fail();
            }
        });
        Button CorrectBtn = findViewById(R.id.CorrectBtn);
        CorrectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Succeed();
            }
        });
        CountDownTimer countDownTimer = new CountDownTimer(Duration * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Fail();
            }
        };
        countDownTimer.start();
    }

    private void Succeed() {
        int Score = 20 * Level;
        //Todo: comeback to word activity and pass the score by level
    }

    private void Fail() {
        //Todo: comeback to word activity and pass the score 0
    }

    private String ChooseWord() {
        Random random = new Random();
        return FilteredList.get(random.nextInt(FilteredList.size())).getTitle();
    }
}
