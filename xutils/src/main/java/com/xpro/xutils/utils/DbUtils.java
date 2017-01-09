package com.xpro.xutils.utils;

import android.util.Log;
import android.widget.Toast;

import com.xpro.xutils.data.City;
import com.xpro.xutils.data.Person;
import com.xpro.xutils.database.DatabaseOpenHelper;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.util.List;

/**
 * 数据库操作复杂，不能光光一个工具类就能解决所有的增删改查操作
 * 这里是具体具体的操作类，个人觉得这样不好
 * ①应该先定义一个借口，然后定义一个实现了该借口的实现类
 * ②或者定义一个类，在该类的方法中将借口作为参数传进去，重写借口中的所有方法
 * Created by huangjh on 2016/12/28 0028 19:26
 */

public class DbUtils {

    private static DbManager db;
    private Person person;
    private City city;

    public DbUtils() {
        db = DatabaseOpenHelper.getInstance();
    }

    public void createTable() {
        person = new Person();
        city = new City();
    }

    public void insert(Object data, int flag) {
        try {
            db.save(data);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


    public void delete(Class table, int flag) {
        try {
            db.deleteById(table, 2);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public void query(Class table, int flag) {
        switch (flag) {
            case 1:
                try {
                    String key = "大傻";
                    List<Person> personList = db.selector(table).where("name", "like", "" + key + "").findAll();
                } catch (DbException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    City city= (City) db.selector(table).findFirst();
                    Log.d("DbUtils", "city:" + city.toString());
                } catch (DbException e) {
                    e.printStackTrace();
                }
        }
    }





}
