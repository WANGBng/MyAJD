package com.bwie.mvp.model;

import com.bwie.my.bean.JiaGouBean;
import com.bwie.my.utils.HttpUtils;

import io.reactivex.Observable;

/**
 * Created by wangbingjun on 2018/11/20.
 */

public class JiaModel {
    public Observable<JiaGouBean> getJiGou(String uid, String pid){
        Observable<JiaGouBean> jiaGouBeanObservable = HttpUtils.getdatanet().api.jiaCar(uid, pid);
        return jiaGouBeanObservable;
    }
}
