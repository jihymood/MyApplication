package com.example.ormlitetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.ormlitetest.bean.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.logger.Log;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by huangjh on 2016/12/22 0022 20:07
 */

public class DaoHelper extends OrmLiteSqliteOpenHelper {
    private Dao<User, Integer> userDao;
    private static DaoHelper daoHelper;

    public DaoHelper(Context context) {
        super(context, "mydatabase.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
            android.util.Log.e("DaoHelper", "onCreate");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, User.class, true);
            TableUtils.createTable(connectionSource, User.class);
            android.util.Log.e("DaoHelper", "onUpgrade");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static synchronized DaoHelper getInstance(Context context) {
        if (daoHelper == null) {
            synchronized (DaoHelper.class) {
                daoHelper = new DaoHelper(context);
            }
        }
        return daoHelper;
    }

    public Dao<User, Integer> getUserDao() throws SQLException {
        if (userDao == null) {
            userDao = getDao(User.class);
        }
        return userDao;
    }

    @Override
    public void close() {
        super.close();
        userDao = null;
    }
}
