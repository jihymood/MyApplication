package com.example.mvptest.mvptest.presenter;

/**
 * Created by HASEE on 2017/5/25 14:10
 */

public interface IUserPresenter {

    void saveUser(int id, String firstName, String lastName);

    void loadUser(int id);
}
