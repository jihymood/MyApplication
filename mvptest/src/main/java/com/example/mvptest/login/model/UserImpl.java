package com.example.mvptest.login.model;

/**
 * Created by HASEE on 2017/5/25 14:21
 * Model负责数据的处理和业务逻辑
 */

public class UserImpl implements IUser {

    private String name;
    private String password;

    public UserImpl() {
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassWord() {
        return password;
    }

    @Override
    public int checkUserValidity(String name, String password) {
        if (name == null || password == null || !name.equals(getName()) || !password.equals(getPassWord())) {
            return -1;
        }
        return 0;
    }

    @Override
    public void login(String name, String password) {

    }
}
