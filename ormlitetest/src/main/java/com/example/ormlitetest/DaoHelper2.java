package com.example.ormlitetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.ormlitetest.bean.City;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by huangjh on 2017/3/3 0003 16:29
 * Email：huangjihy@163.com
 */
public class DaoHelper2 extends OrmLiteSqliteOpenHelper {

    private static DaoHelper2 daoHelper2;

    public DaoHelper2(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, "shabi.db", null, 1);
    }

    public static DaoHelper2 getInstance(Context context) {
        if (daoHelper2 == null) {
            daoHelper2 = new DaoHelper2(context, "shabi.db", null, 1);
        }
        return daoHelper2;
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, City.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, City.class,true);
            TableUtils.createTable(connectionSource, City.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
