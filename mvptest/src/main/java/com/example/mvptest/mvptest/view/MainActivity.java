package com.example.mvptest.mvptest.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mvptest.R;
import com.example.mvptest.mvptest.presenter.IUserPresenter;
import com.example.mvptest.mvptest.presenter.UserPresenterImpl;

public class MainActivity extends AppCompatActivity implements IUserView, View.OnClickListener {

    private EditText mFirstNameEditText, mLastNameEditText, mIdEditText;
    private Button mSaveButton, mLoadButton;
    private IUserPresenter userPresenterImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirstNameEditText = (EditText) findViewById(R.id.first_name_edt);
        mLastNameEditText = (EditText) findViewById(R.id.last_name_edt);
        mIdEditText = (EditText) findViewById(R.id.id_edt);
        mSaveButton = (Button) findViewById(R.id.saveButton);
        mLoadButton = (Button) findViewById(R.id.loadButton);

        mSaveButton.setOnClickListener(this);
        mLoadButton.setOnClickListener(this);

        userPresenterImpl = new UserPresenterImpl(this);
    }


    @Override
    public int getId() {
        return Integer.parseInt(mIdEditText.getText().toString());
    }

    @Override
    public String getFirstName() {
        return mFirstNameEditText.getText().toString();
    }

    @Override
    public String getLastName() {
        return mLastNameEditText.getText().toString();
    }

    @Override
    public void setFirstName(String firstName) {
        mFirstNameEditText.setText(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        mLastNameEditText.setText(lastName);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveButton:
                userPresenterImpl.saveUser(getId(), getFirstName(), getLastName());
                break;
            case R.id.loadButton:
                userPresenterImpl.loadUser(getId());
                break;
        }
    }
}
