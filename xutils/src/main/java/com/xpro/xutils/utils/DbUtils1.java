package com.xpro.xutils.utils;

import com.xpro.xutils.data.City;
import com.xpro.xutils.data.Person;
import com.xpro.xutils.database.DatabaseOpenHelper;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.util.Collection;
import java.util.List;

/**
 * 实现了接口的具体实现类
 * Created by huangjh on 2016/12/28 0028 20:53
 */

public class DbUtils1 implements MyInterface {

    private static DbManager db;
    private Person person;
    private City city;

    public DbUtils1() {
        db = DatabaseOpenHelper.getInstance();
    }

    public void createTable() {
        person = new Person();
        city = new City();
    }

    @Override
    public void save(Object obj) {
        try {
            db.save(obj);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveAll(Collection collection) {

    }

    @Override
    public <T> List<T> queryAll(Class<T> table) {
        return null;
    }

    @Override
    public <T> List<T> queryAll(Class<T> table, String order) {
        return null;
    }

    @Override
    public <T> List<T> queryAll(Class<T> table, String order, int limit) {
        return null;
    }

    @Override
    public <T> T queryById(Class<T> table, Object id) {
        return null;
    }

    /**
     * 清除表
     * @param table
     */
    @Override
    public void clear(Class table) {
        try {
            db.delete(table);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Object obj) {

    }

    @Override
    public void deleteAll(Collection collection) {

    }
}
