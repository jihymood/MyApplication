package com.xpro.xutils.database;

import android.app.Application;

import org.xutils.x;

/**
 * Created by huangjh on 2016/12/28 0028 14:52
 */

public class MyApplication extends Application {
    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false);

    }

//    public static synchronized MyApplication getInstance() {
//        if (myApplication == null) {
//            myApplication = new MyApplication();
//        }
//        return myApplication;
//    }

    public static synchronized MyApplication getInstance1() {
        if (myApplication == null) {
            synchronized (MyApplication.class) {
                myApplication = new MyApplication();
            }
        }
        return myApplication;
    }

}
