package com.example.greedao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by HASEE on 2017/5/17 11:35
 */

@Entity
public class Dog {
    @Id
    private Long id;
    private int height;
    private String color;
    private String name;

    @Keep
    public Dog(Long id, int height, String color, String name) {
        this.id = id;
        this.height = height;
        this.color = color;
        this.name = name;
    }

    @Keep
    public Dog() {
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {


        this.name = name;
    }
}
