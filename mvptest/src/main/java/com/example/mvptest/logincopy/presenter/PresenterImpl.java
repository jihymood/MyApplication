package com.example.mvptest.logincopy.presenter;

import com.example.mvptest.logincopy.model.User;
import com.example.mvptest.logincopy.view.IView;

/**
 * Created by HASEE on 2017/5/31 11:11
 */

public class PresenterImpl implements IPresenter {

    private IView iView;
    private User user;

    @Override
    public void login() {
        iView.login(user.getName(), user.getPassword());
    }

    @Override
    public void clear() {
        iView.clearText();
    }

    public PresenterImpl(IView iView, User user) {
        this.iView = iView;
        this.user = user;
    }
}
