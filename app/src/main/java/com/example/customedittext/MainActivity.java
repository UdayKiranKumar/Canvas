package com.example.customedittext;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    CustomView customView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customView = new CustomView(this);
        setContentView(customView);
    }
}
