package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by huangjh on 2017/2/28 0028 14:02
 * Email：huangjihy@163.com
 */
public class MyService extends Service {

    /*binder对象必须实例化 new DownloadBinder()不可以少*/
    private DownloadBinder binder = new DownloadBinder();

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("MyService", "onBind");
        return binder;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MyService", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("MyService", "onStartCommand");
        Toast.makeText(this, "onStartCommand", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("MyService", "onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("MyService", "onUnbind");
        return super.onUnbind(intent);
    }

    class DownloadBinder extends Binder {
        public void startDownload() {
            Log.e("MyService", "startDownload");
        }

        public void getProgress() {
            Log.e("MyService", "getProgress");
        }
    }

}
