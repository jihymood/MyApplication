package com.example.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by huangjh on 2017/2/28 0028 15:04
 * Email：huangjihy@163.com
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MyIntentService", "onCreate");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("MyIntentService", "onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("MyIntentService", "onBind");
        return super.onBind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("MyIntentService", "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.e("MyIntentService", "Thread id is " + Thread.currentThread().
                getId());
    }
}
