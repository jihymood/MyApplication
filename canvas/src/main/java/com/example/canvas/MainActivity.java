package com.example.canvas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.view.BezierView;

public class MainActivity extends AppCompatActivity {

    private BezierView bezier;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bezier = (BezierView) findViewById(R.id.bezier);

    }
}
