package com.bwie.mvp.model;

import com.bwie.my.bean.HomeBean;
import com.bwie.my.utils.HttpUtils;

import io.reactivex.Observable;

/**
 * Created by wangbingjun on 2018/11/13.
 */

public class MainModel {
    public Observable<HomeBean> getData(){
        Observable<HomeBean> shouye = HttpUtils.getdatanet().api.shouye();
        return shouye;
    }
}
