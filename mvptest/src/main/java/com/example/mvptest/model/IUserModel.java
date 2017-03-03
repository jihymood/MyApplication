package com.example.mvptest.model;

import com.example.mvptest.bean.UserBean;

/**
 * Created by huangjh on 2017/3/2 0002 17:33
 * Email：huangjihy@163.com
 */
public interface IUserModel {

    void setId(int id);

    void setFirstName(String firstName);

    void setLastName(String lastName);

    UserBean load(int id);//通过id读取user信息,返回一个UserBean


}
