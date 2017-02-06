package com.example.notification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnStart;
    private Button btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    /**
     * IllegalArgumentException: Service Intent must be explicit
     * 经过查找相关资料，发现是因为Android5.0中service的intent一定要显性声明，当这样绑定的时候不会报错。
     *
     * final Intent intent = new Intent(this,BindService.class);
     * bindService(intent,coon,Service.BIND_AUTO_CREATE)
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnStart) {
            // 启动Service
            Intent intent = new Intent(this, NotificationService.class);//显示Intent
            intent.setAction("ymw.MY_SERVICE");
            startService(intent);
        }
        if (id == R.id.btnStop) {
            // 关闭Service
            Intent intent = new Intent(this, NotificationService.class);
            intent.setAction("ymw.MY_SERVICE");
            stopService(intent);
        }
    }

    @Override
    public void onBackPressed() {
        System.exit(0);
        super.onBackPressed();
    }
}
