package com.paxsz.mvp_sample.Presenter;

/**
 * Created by yousheng on 17/4/11.
 */


import android.util.Log;

import com.paxsz.mvp_sample.Bean.UserBean;
import com.paxsz.mvp_sample.Model.IUserModel;
import com.paxsz.mvp_sample.Model.UserModel;
import com.paxsz.mvp_sample.View.IUserView;

/**
 * (4)Presenter:它能拥有m和v层的接口实例
 * Presenter就能通过接口与View及Model进行交互了：
 * 主要就是save和load两个方法的具体逻辑，在两个方法中调用m和v层的接口中规定好的方法
 */
public class UserPresenter implements IUserPresenter{
    private IUserView userView;
    private IUserModel userModel;

    //将带有具体方法实现的实例upcast成为接口
    public UserPresenter(IUserView userView) {
        this.userView = userView;
        userModel=new UserModel();
    }

    //此方法将view层获取的数据存入model层，且只用到了接口里的方法
    public void saveUser(){
        if(userView.getUserName()==null||userView.getUserPassword()==null){
            Log.d("test", "saveUser: 账号或者密码不能为空");
        }else{
            userModel.saveUser(userView.getID(),userView.getUserName(),userView.getUserPassword());
            Log.d("test", "saveUser: success!");
        }
    }

    public void loadUser() {
        UserBean userBean = userModel.loadUser(userView.getID());
        if (userBean != null) {
            userView.setUserName(userBean.getUser());
            userView.setUserPassword(userBean.getPassword());
        }else Log.d("test", "loadUser: 编号为id的用户数据不存在");
    }
}
