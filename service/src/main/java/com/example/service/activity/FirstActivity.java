package com.example.service.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.service.BaseActivity;
import com.example.service.R;
import com.example.service.service.ReliefTaskStateService;

public class FirstActivity extends BaseActivity {

    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstActivity.this, ReliefTaskStateService.class);
                startService(intent);
            }
        });
    }
}
