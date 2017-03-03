package com.example.ormlitetest.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by huangjh on 2017/3/3 0003 16:40
 * Email：huangjihy@163.com
 */
@DatabaseTable(tableName = "dog")
public class Dog {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "age")
    private String age;
    @DatabaseField(columnName = "height")
    private String height;
    @DatabaseField(columnName = "sex")
    private String sex;
    @DatabaseField(columnName = "address")
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
