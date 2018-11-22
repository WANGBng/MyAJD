package com.bwie.mvp.model;

import com.bwie.my.bean.LoginBean;
import com.bwie.my.bean.RegisBean;
import com.bwie.my.utils.HttpUtils;

import io.reactivex.Observable;

/**
 * Created by wangbingjun on 2018/11/13.
 */

public class LoginModel {
    public Observable<LoginBean> getData(String mobile, String password){
        Observable<LoginBean> data = HttpUtils.getdatanet().api.loginData(mobile, password);
        return data;
    }
    public Observable<RegisBean> getReg(String mobile, String password){
        Observable<RegisBean> data = HttpUtils.getdatanet().api.RegData(mobile, password);
        return data;
    }
}
