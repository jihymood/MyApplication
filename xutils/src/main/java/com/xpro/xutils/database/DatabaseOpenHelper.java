package com.xpro.xutils.database;

import android.content.Context;

import org.xutils.DbManager;
import org.xutils.x;

/**
 * Created by huangjh on 2016/12/28 0028 14:11
 */

public class DatabaseOpenHelper {
    private DbManager.DaoConfig daoConfig;
    private Context context;
    private static DbManager db;
    private final String NAME = "xutils.db";
    private final int VERSON = 1;

    private static DatabaseOpenHelper helper;

    public DatabaseOpenHelper() {
        daoConfig = new DbManager.DaoConfig()
                .setDbName(NAME)
                .setDbVersion(VERSON)
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        db.getDatabase().enableWriteAheadLogging();
                    }
                })
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                    }
                });
        db = x.getDb(daoConfig);
    }

    public static DbManager getInstance() {
        if (db == null) {
            DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper();
        }
        return db;
    }

}
