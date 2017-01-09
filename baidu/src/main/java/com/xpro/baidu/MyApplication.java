package com.xpro.baidu;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by huangjh on 2016/12/30 0030 17:39
 */

public class MyApplication extends Application {

    private static MyApplication application;

    public static synchronized MyApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());

        application=this;
    }
}
