package com.example.greedao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by HASEE on 2017/5/31 08:45
 */

@Entity
public class Cat {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String color;

    @Generated(hash = 288057719)
    public Cat(Long id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    @Generated(hash = 205319056)
    public Cat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
