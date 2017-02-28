package com.example.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Date;

/**
 * Created by huangjh on 2017/2/28 0028 15:24
 * Email：huangjihy@163.com
 */
public class LongRunningService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("LongRunningService", "executed at " + new Date().toString());
            }
        }).start();
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int time = 10 * 1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + time;
        Intent intent1 = new Intent(this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent1, 0);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);

        return super.onStartCommand(intent, flags, startId);
    }
}
