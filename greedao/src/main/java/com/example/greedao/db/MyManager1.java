package com.example.greedao.db;

import android.content.Context;

import com.example.greendao.gen.DaoMaster;
import com.example.greendao.gen.DaoSession;

/**
 * Created by HASEE on 2017/6/1 08:46
 */

public class MyManager1 {

    private DaoMaster.DevOpenHelper openHelper;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private static MyManager1 instance;

    public MyManager1 getInstance(Context context) {
        if (instance == null) {
            synchronized (MyManager1.class) {
                instance = new MyManager1(context);
            }
        }
        return instance;
    }

    public MyManager1(Context context) {
        openHelper = new DaoMaster.DevOpenHelper(context, "my.db", null);
        daoMaster = new DaoMaster(openHelper.getWritableDb());
        daoSession = daoMaster.newSession();
    }
}
