package com.example.zhangqian.myapplication.entity;

/**
 * Created by zhangqian on 2016/12/19 0019.
 */

public class User {
    private String address;
    private String name;
    private int age;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String address, int age, String name) {
        this.address = address;
        this.age = age;
        this.name = name;
    }

    public User() {
        super();
    }

    @Override
    public String toString() {
        return "User{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
