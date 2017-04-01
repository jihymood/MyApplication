package com.example.service.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by huangjh on 2017/2/28 0028 15:30
 * Email：huangjihy@163.com
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent i = new Intent(context, LongRunningService.class);
        context.startService(i);
    }


}
