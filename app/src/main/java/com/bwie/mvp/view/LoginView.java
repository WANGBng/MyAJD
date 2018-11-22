package com.bwie.mvp.view;

import com.bwie.base.BaseView;
import com.bwie.my.bean.LoginBean;
import com.bwie.my.bean.RegisBean;

/**
 * Created by wangbingjun on 2018/11/13.
 */

public interface LoginView extends BaseView {
    void onLoginSuccess(LoginBean loginBean);
    void onRegSuccess(RegisBean regisBean);
    void onError(LoginBean loginBean);
    void onErrorReg(RegisBean regisBean);
}
