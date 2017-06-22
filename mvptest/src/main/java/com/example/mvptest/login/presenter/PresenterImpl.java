package com.example.mvptest.login.presenter;

import com.example.mvptest.login.model.IUser;
import com.example.mvptest.login.view.IView;

/**
 * Created by HASEE on 2017/5/26 08:54
 *
 * Presenter负责做View和Model的中间人
 */

public class PresenterImpl implements IPresenter {

    private IUser iUser;
    private IView iView;

    public PresenterImpl(IUser iUser, IView iView) {
        this.iUser = iUser;
        this.iView = iView;
    }

    @Override
    public void login(String name, String pwd) {
        //登陆逻辑,登陆成功调用IView接口的doLoginResult方法
        iUser.login(name, pwd);
        iView.showLoginSuccessMsg(name,pwd); //登陆成功提示


    }

    @Override
    public void clear() {

    }

    /**
     * 5/26理解
     * 也可以login(String name, String pwd)有两个参数，这样可以直接传给iView.login()这个方法使用
     * 但是这里没有传，是因为使用了IUser接口的方法。IUser接口是model中的，专门作存储数据作用，
     * 如果IPresenter接口的login(String name, String pwd)有两个参数，则可以在Activity中获得name和password
     *
     * 6/22理解
     * PresenterImpl应该是实现逻辑的类，具体的操作处理在这儿处理，
     * 不应该像下面的这个方法封装了iView.login(iUser.getName(), iUser.getPassWord());
     * 这样的话IView的功能就是处理逻辑部分了，与mvp模式不符合，V层只是负责数据展示
     *
     */
//    @Override
//    public void login() {  //该方法有误
//        iView.login(iUser.getName(), iUser.getPassWord());
//    }
//
//
//    @Override
//    public void clear() {
//        iView.clear();
//    }


}
