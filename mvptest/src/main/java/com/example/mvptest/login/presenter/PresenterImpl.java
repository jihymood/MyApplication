package com.example.mvptest.login.presenter;

import com.example.mvptest.login.model.IUser;
import com.example.mvptest.login.view.IView;

/**
 * Created by HASEE on 2017/5/26 08:54
 */

public class PresenterImpl implements IPresenter {

    private IUser iUser;
    private IView iView;

    public PresenterImpl(IUser iUser, IView iView) {
        this.iUser = iUser;
        this.iView = iView;
    }

    /**
     * 也可以login(String name, String pwd)有两个参数，这样可以直接传给iView.login()这个方法使用
     * 但是这里没有传，是因为使用了IUser接口的方法。IUser接口是model中的，专门作存储数据作用，
     * 如果IPresenter接口的login(String name, String pwd)有两个参数，则可以在Activity中获得name和password
     */
    @Override
    public void login() {
        iView.login(iUser.getName(), iUser.getPassWord());
    }

    @Override
    public void clear() {
        iView.clear();
    }
}
