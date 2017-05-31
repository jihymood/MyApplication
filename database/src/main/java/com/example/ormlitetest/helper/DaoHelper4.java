package com.example.ormlitetest.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.ormlitetest.bean.Dog;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by HASEE on 2017/5/31 09:24
 */

public class DaoHelper4 extends OrmLiteSqliteOpenHelper {

    private static DaoHelper4 daoHelper4;
    private Dao<Dog, Integer> dogDao;

    public DaoHelper4(Context context) {
        super(context, "mydatabase.db", null, 1);

    }

    public DaoHelper4 getInstance(Context context) {
        if (daoHelper4 == null) {
            synchronized (DaoHelper4.class) {
                daoHelper4 = new DaoHelper4(context);
            }
        }
        return daoHelper4;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Dog.class);
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

    public Dao<Dog, Integer> getDao() {
        try {
            dogDao = getDao(Dog.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dogDao;
    }

    @Override
    public void close() {
        super.close();
        dogDao = null;
    }
}
