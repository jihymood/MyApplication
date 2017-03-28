package com.example.ormlitetest.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.ormlitetest.bean.Dog;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by huangjh on 2017/3/24 0024 14:36
 * Email：huangjihy@163.com
 */
public class DaoHelper3 extends OrmLiteSqliteOpenHelper {

    private static DaoHelper3 daoHelper3;
    private Dao<Dog, Integer> dogDao;

    public DaoHelper3(Context context) {
        super(context, "hh.db", null, 1);
    }

    public static DaoHelper3 getInstance(Context context) {
        if (daoHelper3 == null) {
            synchronized (DaoHelper3.class) {
                daoHelper3 = new DaoHelper3(context);
            }
        }
        return daoHelper3;
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Dog.class);
            Log.d("DaoHelper3", "表创建成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Dog.class, true);
            TableUtils.createTable(connectionSource, Dog.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Dog, Integer> getDogDao() {
        if (dogDao == null) {
            try {
                dogDao = getDao(Dog.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dogDao;
    }

    @Override
    public void close() {
        super.close();
        dogDao = null;
    }
}
