package com.example.mvptest.login.presenter;

/**
 * Created by HASEE on 2017/5/26 08:51
 */

public interface IPresenter {

//    void login();

    void login(String name,String pwd); //应该传入参数

    void clear();

}
