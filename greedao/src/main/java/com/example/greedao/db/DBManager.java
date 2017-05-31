package com.example.greedao.db;

import android.content.Context;

import com.example.greendao.gen.DaoMaster;
import com.example.greendao.gen.DaoSession;

/**
 * Created by HASEE on 2017/5/17 09:38
 */

public class DBManager {
    private final static String dbName = "test.db";
    private static DBManager instance;
    private Context context;
    private DaoMaster.DevOpenHelper openHelper;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    public DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        daoMaster = new DaoMaster(openHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();

    }

    public static DBManager getInstance(Context context) {
        if (null == instance) {
            synchronized (DBManager.class) {
                instance = new DBManager(context);
            }
        }
        return instance;
    }

}
