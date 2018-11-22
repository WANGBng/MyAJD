package com.bwie.mvp.view;

import com.bwie.base.BaseView;
import com.bwie.my.bean.CartBean;

/**
 * Created by wangbingjun on 2018/11/19.
 */

public interface CartView extends BaseView {
    void onCarSuccess(CartBean carBean);
    void onCarError(Throwable t);
}
