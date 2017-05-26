package com.example.mvptest.login.model;

/**
 * Created by HASEE on 2017/5/25 14:19
 */

public interface IUser {

    String getName();

    String getPassWord();

    int checkUserValidity(String name, String password);


}
