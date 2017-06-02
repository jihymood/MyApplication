package com.example.greedao.db;

import android.content.Context;

import com.example.greendao.gen.DaoMaster;
import com.example.greendao.gen.DaoSession;

/**
 * Created by HASEE on 2017/5/27 15:26
 */

public class MyManager {

    private static MyManager instance;
    private DaoMaster.DevOpenHelper helper;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
8
    public static MyManager getInstance(Context context) {

        if (instance == null) {
            synchronized (MyManager.class) {
                instance = new MyManager(context);
            }
        }
        return instance;
    }

    public MyManager(Context context) {
        helper = new DaoMaster.DevOpenHelper(context, "mytest.db", null);
        daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    public DaoMaster getDaoMaster() {
        return daoMaster;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
