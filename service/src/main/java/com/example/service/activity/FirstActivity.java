package com.example.service.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.service.BaseActivity;
import com.example.service.R;
import com.example.service.service.ReliefTaskStateService;

public class FirstActivity extends BaseActivity {

    private Button btn;
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        btn = (Button) findViewById(R.id.btn);
        textview = (TextView) findViewById(R.id.textview);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, ReliefTaskStateService.class);
                startService(intent);
            }
        });

        registerReceiver();

    }


    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);

    }

    private void registerReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.zhanghao.msg");
        registerReceiver(broadcastReceiver, filter);
    }


    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("com.zhanghao.msg")) {
                String stringMsg = intent.getStringExtra("msg");
                textview.setText(stringMsg);

            }
        }
    };


}
