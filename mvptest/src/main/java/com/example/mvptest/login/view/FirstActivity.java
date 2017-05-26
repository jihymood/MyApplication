package com.example.mvptest.login.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mvptest.R;
import com.example.mvptest.login.model.IUser;
import com.example.mvptest.login.model.UserImpl;
import com.example.mvptest.login.presenter.IPresenter;
import com.example.mvptest.login.presenter.PresenterImpl;

public class FirstActivity extends AppCompatActivity implements IView{

    private IPresenter iPresenter;
    private IUser iUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        iUser = new UserImpl("傻逼", "123");
        iPresenter = new PresenterImpl(iUser, this);
        iPresenter.login();
    }


    @Override
    public void clear() {


    }

    @Override
    public void login(String name, String pwd) {
        //IPresenter接口中login方法里又调用了IView接口的login方法，即这个类中的该方法
    }
}
