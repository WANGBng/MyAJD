package com.bwie.mvp.view;

import com.bwie.base.BaseView;
import com.bwie.my.bean.JiaGouBean;

/**
 * Created by wangbingjun on 2018/11/20.
 */

public interface JiaView extends BaseView {
    void onJiaSuccess(JiaGouBean jiaGouBean);
    void onJiaError(Throwable t);
}
