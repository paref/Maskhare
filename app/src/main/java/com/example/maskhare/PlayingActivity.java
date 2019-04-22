package com.example.maskhare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class PlayingActivity extends AppCompatActivity {
    int Round;
    int Duration;
    int Level;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);
        Intent intent = getIntent();
        Round = intent.getIntExtra("Round",1);
        Duration = intent.getIntExtra("Duration",1);
        Level = intent.getIntExtra("Level",1);//
    }
}
