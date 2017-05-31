package com.example.ormlitetest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.ormlitetest.R;
import com.example.ormlitetest.bean.City;
import com.example.ormlitetest.bean.Dog;
import com.example.ormlitetest.bean.User;
import com.example.ormlitetest.helper.DaoHelper;
import com.example.ormlitetest.helper.DaoHelper1;
import com.example.ormlitetest.helper.DaoHelper3;
import com.example.ormlitetest.parse.CityParseEntity;
import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DaoHelper daoHelper;
    private DaoHelper1 daoHelper1;
    private DaoHelper3 daoHelper3;
    private Dao<User, Integer> userDao;
    private Dao<City, Integer> cityDao;
    private Dao<Dog, Integer> dogDao;

    private String json = "{\"success\": \"true\", "
            + "\"body\":  {\"data\":[{\"province\": \"江苏省\", "
            + "\"city\": \"连云港\",\"country\": "
            + "\"灌云县\", \"code\": 222200,\"town\": \"南岗乡\"},  {"
            + "\"province\": \"江苏省\", "
            + "\"city\": \"连云港\",\"country\": "
            + "\"灌南县\", \"code\": 222200,\"town\": \"南岗乡\"},{\"province\": \"江苏省\", "
            + "\"city\": \"连云港\",\"country\": "
            + "\"新浦区\", \"code\": 222200,\"town\": \"南岗乡\"},{\"province\": \"江苏省\", "
            + "\"city\": \"连云港\",\"country\": "
            + "\"海州区\", \"code\": 222200,\"town\": \"南岗乡\"},{\"province\": \"江苏省\", "
            + "\"city\": \"连云港\",\"country\": "
            + "\"东海县\", \"code\": 222200,\"town\": \"南岗乡\"},{\"province\": \"江苏省\", "
            + "\"city\": \"南京\",\"country\": "
            + "\"浦口区\", \"code\": 222200,\"town\": \"南岗乡\"}]}, \"errorCode\": \"-1\",\"msg\": \"成功\"}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("MainActivity", json);
        try {
            daoHelper = DaoHelper.getInstance(this);
            userDao = daoHelper.getUserDao();

            daoHelper1 = DaoHelper1.getInstance(this);
            cityDao = daoHelper1.getCityDao();

            daoHelper3 = DaoHelper3.getInstance(this);
            dogDao = daoHelper3.getDogDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        deleteUser();
//
//        xiugaiUser();
//
//        queryUser();

        insertCity();

    }

    /**
     * 添加狗数据
     */
    public void insertDog() {
        for (int i = 0; i < 5; i++) {
            try {
                Dog dog = new Dog("南京", "12" + i, "13", "母");
                dogDao.createIfNotExists(dog);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertUser() {
        try {
            for (int i = 0; i < 100; i++) {
                User user = new User(12, "大傻逼" + i);
                userDao.createIfNotExists(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser() {
        DeleteBuilder<User, Integer> deleteBuilder = null;
        try {
            deleteBuilder = userDao.deleteBuilder();
            deleteBuilder.where().le("id", 84);

            ArrayList<Integer> ids = new ArrayList<>();
            ids.add(3);
            ids.add(4);
            userDao.deleteIds(ids);
            userDao.delete(userDao.queryForId(5));
            userDao.deleteById(6);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void xiugaiUser() {
        UpdateBuilder<User, Integer> updateBuilder = null;
        try {
            updateBuilder = userDao.updateBuilder();
            updateBuilder.where().eq("id", 7);  //eq字段？
            updateBuilder.updateColumnValue("name", "黄继海");
            updateBuilder.update();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void queryUser() {
        try {
            User user = userDao.queryForId(7);
            if (user != null) {
                Log.e("MainActivity", user.getName() + user.getAge() + user.getId());
            }

            List<User> list = userDao.queryForEq("name", "黄继海");
            for (User user1 : list
                    ) {
                Log.e("MainActivity", user1.getName() + user1.getAge() + user1.getId());
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertCity() {
        try {
            Gson gson = new Gson();
            CityParseEntity entity = gson.fromJson(json, CityParseEntity.class);
            List<City> dataBeenList = entity.getBody().getData();
            for (int i = 0; i < dataBeenList.size(); i++) {
                City dataBean = dataBeenList.get(i);
                cityDao.createIfNotExists(dataBean);
            }


//            for (int i = 0; i < 5; i++) {
//                City city = new City("灌云县", "连云港", "江苏省", "伊山镇" + i,"222200");
//                cityDao.createIfNotExists(city);
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
