package com.example.ormlitetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.ormlitetest.bean.City;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by huangjh on 2016/12/24 0024 11:04
 */


public class DaoHelper1 extends OrmLiteSqliteOpenHelper {
    private static DaoHelper1 helper;
    private Dao<City, Integer> cityDao;

    /**
     * 重写DaoHelper1的构造方法
     * 构造函数私有的，这样类就不提供默认的构造函数了
     * @param context
     */
    private DaoHelper1(Context context) {
        super(context, "shabi.db", null, 2);
    }

    public static synchronized DaoHelper1 getInstance(Context context) {
        if (helper == null) {
            synchronized (DaoHelper1.class) {
                helper = new DaoHelper1(context);
            }
        }
        return helper;

    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, City.class);
            android.util.Log.e("DaoHelper", "onCreate");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, City.class, true);
            TableUtils.createTable(connectionSource, City.class);
            android.util.Log.e("DaoHelper", "onUpgrade");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<City, Integer> getCityDao() throws SQLException {
        if (cityDao == null) {
            cityDao = getDao(City.class);
        }
        return cityDao;
    }


    @Override
    public void close() {
        super.close();
        cityDao = null;
    }
}
