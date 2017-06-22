package com.example.mvptest.login.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mvptest.R;
import com.example.mvptest.login.model.UserImpl;
import com.example.mvptest.login.presenter.IPresenter;
import com.example.mvptest.login.presenter.PresenterImpl;

public class FirstActivity extends AppCompatActivity implements IView{

    private IPresenter iPresenter;
    private String name,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        iPresenter = new PresenterImpl(new UserImpl(), this);

//        name=text.ge;
//        pwd=e.get;
        iPresenter.login(name,pwd);


    }


    @Override
    public void clear() {


    }

    /**
     * IView接口中的方法不能有处理登陆的逻辑，因为IView接口的功能只是展示在页面上，如果有登陆逻辑就与mvp模式不符
     * 所以IView接口中的方法应该是登陆后的状态 onLoginResult()，以及清除输入框 onClearText()
     * @param name
     * @param pwd
     */
    @Override
    public void showLoginSuccessMsg(String name, String pwd) {  // 方法名不对，应改成 onLoginResult()
        //IPresenter接口中login方法里又调用了IView接口的login方法，即这个类中的该方法


    }
}
