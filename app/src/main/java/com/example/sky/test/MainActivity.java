package com.example.sky.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private BubbleProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = (BubbleProgressBar) findViewById(R.id.bar);
        bar.start(this);
    }

    public void onClick(View view) {
        bar.stop();
    }
}
