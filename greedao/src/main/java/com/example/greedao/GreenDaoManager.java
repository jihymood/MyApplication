package com.example.greedao;

import android.content.Context;

import com.example.greendao.gen.DaoMaster;
import com.example.greendao.gen.DaoSession;

/**
 * Created by HASEE on 2017/5/17 10:29
 */

public class GreenDaoManager {
    private static GreenDaoManager mInstance;
    private DaoMaster.DevOpenHelper devOpenHelper;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;


    private GreenDaoManager(Context context) {
        devOpenHelper = new DaoMaster.DevOpenHelper(
                context, "notes.db", null);
        mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public static GreenDaoManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new GreenDaoManager(context);
        }
        return mInstance;
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

//    public DaoSession getNewSession() {
//        mDaoSession = mDaoMaster.newSession();
//        return mDaoSession;
//    }
}
