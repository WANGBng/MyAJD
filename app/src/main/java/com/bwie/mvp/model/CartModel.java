package com.bwie.mvp.model;

import com.bwie.my.bean.CartBean;
import com.bwie.my.utils.HttpUtils;

import io.reactivex.Observable;

/**
 * Created by wangbingjun on 2018/11/20.
 */

public class CartModel {

    public Observable<CartBean> getCartData(String uid,String token){
        Observable<CartBean> car = HttpUtils.getdatanet().api.getCar(uid, token);
        return car;
    }

}
