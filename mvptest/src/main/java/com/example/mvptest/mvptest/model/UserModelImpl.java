package com.example.mvptest.mvptest.model;

import android.util.SparseArray;

import com.example.mvptest.mvptest.bean.UserBean;

/**
 * Created by huangjh on 2017/3/2 0002 17:38
 * Email：huangjihy@163.com
 */
public class UserModelImpl implements IUserModel {

    private String mFristName;
    private String mLastName;
    private int mID;

    private SparseArray<UserBean> mUsererArray = new SparseArray<UserBean>();

    @Override
    public void setId(int id) {
        // TODO Auto-generated method stub
        mID = id;
    }

    @Override
    public void setFirstName(String firstName) {
        // TODO Auto-generated method stub
        mFristName = firstName;
    }

    @Override
    public void setLastName(String lastName) {
        // TODO Auto-generated method stub
        mLastName = lastName;
        UserBean UserBean = new UserBean(mFristName, mLastName);
        mUsererArray.append(mID, UserBean);
    }

    @Override
    public UserBean load(int id) {
        // TODO Auto-generated method stub
        mID = id;
        UserBean userBean = mUsererArray.get(mID, new UserBean("not found",
                "not found"));
        return userBean;

    }

}
