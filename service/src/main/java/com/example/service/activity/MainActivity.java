package com.example.service.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.service.BaseActivity;
import com.example.service.R;
import com.example.service.service.LongRunningService;
import com.example.service.service.MyIntentService;
import com.example.service.service.MyService;

/**
 * 服务的用法，参考第一行代码
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button start_service, stop_service;
    private Button bindService;
    private Button unbindService;
    private Button startIntentService;
    private Button start_long_service;
    private Button first;
    private MyService.DownloadBinder downloadBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("MyService", "onServiceConnected");
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("MyService", "onServiceDisconnected");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start_service = (Button) findViewById(R.id.start_service);
        stop_service = (Button) findViewById(R.id.stop_service);
        bindService = (Button) findViewById(R.id.bind_service);
        unbindService = (Button) findViewById(R.id.unbind_service);
        startIntentService = (Button) findViewById(R.id.start_intent_service);
        start_long_service = (Button) findViewById(R.id.start_long_service);
        first = (Button) findViewById(R.id.first);
        startIntentService.setOnClickListener(this);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
        start_service.setOnClickListener(this);
        stop_service.setOnClickListener(this);
        start_long_service.setOnClickListener(this);
        first.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service:
                Intent intent = new Intent(this, MyService.class);
                startService(intent);
                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent); // 停止服务
                break;
            case R.id.bind_service:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE); // 绑定服务
                break;
            case R.id.unbind_service:
                unbindService(connection); // 解绑服务
                break;
            case R.id.start_intent_service:// 打印主线程的id
                Log.e("MyIntentService", "MainThread id is " + Thread.currentThread().
                        getId());
                Intent intentService = new Intent(this, MyIntentService.class);
                startService(intentService);
                break;
            case R.id.start_long_service:
                Intent intent1 = new Intent(this, LongRunningService.class);
                startService(intent1);
                break;
            case R.id.first:
                Intent intent2 = new Intent(this, FirstActivity.class);
                startActivity(intent2);
                break;
        }
    }

}
