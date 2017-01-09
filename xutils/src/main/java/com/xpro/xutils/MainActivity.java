package com.xpro.xutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xpro.xutils.data.City;
import com.xpro.xutils.data.Person;
import com.xpro.xutils.entity.CityBody;
import com.xpro.xutils.entity.CityRoot;
import com.xpro.xutils.utils.DbUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.text)
    TextView text;
    @Bind(R.id.insert)
    Button insert;
    @Bind(R.id.xiugai)
    Button xiugai;
    @Bind(R.id.delete)
    Button delete;
    @Bind(R.id.query)
    Button query;
    @Bind(R.id.insertJson)
    Button insertJson;

    private DbUtils utils;
    private DbUtils utils1;
    private Person person;
    private City city;
    private String json = "{\"success\": \"true\", "
            + "\"body\":  {\"data\":[{\"province\": \"江苏省\", "
            + "\"city\": \"连云港\",\"country\": "
            + "\"灌云县\", \"code\": 222200},  {"
            + "\"province\": \"江苏省\", "
            + "\"city\": \"连云港\",\"country\": "
            + "\"灌南县\", \"code\": 222200},{\"province\": \"江苏省\", "
            + "\"city\": \"连云港\",\"country\": "
            + "\"新浦区\", \"code\": 222200},{\"province\": \"江苏省\", "
            + "\"city\": \"连云港\",\"country\": "
            + "\"海州区\", \"code\": 222200},{\"province\": \"江苏省\", "
            + "\"city\": \"连云港\",\"country\": "
            + "\"东海县\", \"code\": 222200},{\"province\": \"江苏省\", "
            + "\"city\": \"南京\",\"country\": "
            + "\"浦口区\", \"code\": 222200}]}, \"errorCode\": \"-1\",\"msg\": \"成功\"}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        utils1=new DbUtils();
        
        utils = new DbUtils();
        utils.createTable();


    }

    @OnClick({R.id.insert, R.id.xiugai, R.id.delete, R.id.query})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.insert:
                for (int i = 0; i < 100; i++) {
                    Person person = new Person("南京", 23, "二傻", "4000");
                    utils.insert(person, 1);
                }
                break;
            case R.id.xiugai:
                break;
            case R.id.delete:

                break;
            case R.id.query:
                /**
                 * 查询第一条数据
                 */
//                try {
//                    Person person=db.findFirst(Person.class);
//                    Log.d("MainActivity", "person:" + person);
//                    Toast.makeText(this, "person:" + person, Toast.LENGTH_SHORT).show();
//                } catch (DbException e) {
//                    e.printStackTrace();
//                }
                /**
                 * 模糊查询
                 */
//                try {
//                    db.delete(Person.class);
//                    List<Person> list=db.selector(Person.class).where("name", "like", "meiyou").findAll();
//                    if (null != list && list.size() > 0) {
//                        text.setText(list.get(0).toString());
//                    }
//                    Toast.makeText(this, "没有查询到数据", Toast.LENGTH_SHORT).show();
//                } catch (DbException e) {
//                    e.printStackTrace();
//                }

                utils.query(Person.class, 1);//查询Person表中的所有“大傻”;
                utils.query(City.class, 2);


                break;
        }
    }

    @OnClick(R.id.insertJson)
    public void onClick() {
        Gson gson = new Gson();
        CityRoot cityRoot = gson.fromJson(json, CityRoot.class);
        CityBody cityBody = cityRoot.getBody();
        List<City> cityList = cityBody.getData();
        if (cityList != null && cityList.size() > 0) {
            for (int i = 0; i < cityList.size(); i++) {
                City city = cityList.get(i);
                utils.insert(city, 1);
            }
        }

    }
}
