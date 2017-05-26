package com.example.mvptest.mvptest.presenter;

import com.example.mvptest.mvptest.bean.UserBean;
import com.example.mvptest.mvptest.model.IUserModel;
import com.example.mvptest.mvptest.model.UserModelImpl;
import com.example.mvptest.mvptest.view.IUserView;

/**
 * Created by huangjh on 2017/3/2 0002 17:34
 * Email：huangjihy@163.com
 */
public class UserPresenterImpl implements IUserPresenter {
    /**
     * 这里为什么用到多态？IUserModel是接口，UserModel是实现类
     * 接口引用指向实现类的对象
     */
    private IUserModel iUserModel;
    //    private UserModelImpl userModel;
    private IUserView iUserView;

    public UserPresenterImpl(IUserView iUserView) {
        this.iUserView = iUserView;
        iUserModel = new UserModelImpl();
    }


    @Override
    public void saveUser(int id, String firstName, String lastName) {
        iUserModel.setId(id);
        iUserModel.setFirstName(firstName);
        iUserModel.setLastName(lastName);
//        userModel.setId(id);
//        userModel.setFirstName(firstName);
//        userModel.setLastName(lastName);
    }

    @Override
    public void loadUser(int id) {
        UserBean user = iUserModel.load(id);
//        UserBean user=userModel.load(id);
        iUserView.setFirstName(user.getFirstName());//通过调用IUserView的方法来更新显示
        iUserView.setLastName(user.getLastName());
    }
}
