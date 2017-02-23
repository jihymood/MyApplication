package com.xpro.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        btn = (Button) findViewById(R.id.btn);
        btn.setWidth(11);
        btn.setHeight(12);
    }
}
