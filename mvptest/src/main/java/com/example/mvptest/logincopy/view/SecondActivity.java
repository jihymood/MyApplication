package com.example.mvptest.logincopy.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mvptest.R;
import com.example.mvptest.logincopy.model.User;
import com.example.mvptest.logincopy.presenter.IPresenter;
import com.example.mvptest.logincopy.presenter.PresenterImpl;

public class SecondActivity extends AppCompatActivity implements IView, View.OnClickListener {

    private EditText et_name, et_pwd;
    private Button bt_login, bt_clear;
    private IPresenter presenter;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        et_name = (EditText) findViewById(R.id.et_name);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_clear = (Button) findViewById(R.id.bt_clear);

        bt_login.setOnClickListener(this);
        bt_clear.setOnClickListener(this);

        user = new User(et_name.getText().toString(), et_pwd.getText().toString());
        presenter = new PresenterImpl(this, user);

    }

    @Override
    public void login(String name, String pwd) {
        if (name != null) {


        }
    }

    @Override
    public void clearText() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                presenter.login();
                break;
        }

    }
}
