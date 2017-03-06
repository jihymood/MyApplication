package com.xpro.test1;

import android.app.Application;

import org.xutils.x;

/**
 * 全局配置信息
 */
public class MainApplication extends Application {
    private static Application mApplication;
    public static int mNetWorkState;

    public static synchronized Application getInstance() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false);

        mApplication = this;
    }

}
