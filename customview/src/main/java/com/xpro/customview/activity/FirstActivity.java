package com.xpro.customview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xpro.customview.R;
import com.xpro.customview.views.ExtendButton;
import com.xpro.customview.views.ExtendButton2;

public class FirstActivity extends AppCompatActivity {

    private ExtendButton expand_btn;
    private ExtendButton2 expand_btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        expand_btn = (ExtendButton) findViewById(R.id.expand_btn);
        expand_btn2 = (ExtendButton2) findViewById(R.id.expand_btn2);

        new Thread(expand_btn).start();
//        new Thread(expand_btn1).start();

    }

}
