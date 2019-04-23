package com.example.maskhare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        String str = readFromFile();
        String[] strings = str.split("\r\n");
        int n = 0;
        String[] ss = strings[0].split(",");
        n = ss.length;
        List<Integer> ints = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ints.add(0);
        }
        for (String s : strings) {
            String[] split = s.split(",");
            for (int i = 0; i < n; i++) {
                ints.set(i, ints.get(i) + Integer.valueOf(split[i]));
            }
        }
        //Todo:Show list in listview
    }

    private void ClearFile() {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.openFileOutput("text.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write("");
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFromFile() {
        try {
            File file = new File(Environment.getExternalStorageDirectory() + "/test.txt");
            FileReader reader = new FileReader(file);
            char[] chars = new char[500];
            reader.read(chars);
            file.delete();
            return chars.toString();
        } catch (IOException e) {
            Log.e("wtf", "wtf");
            return null;
        }
    }
}
