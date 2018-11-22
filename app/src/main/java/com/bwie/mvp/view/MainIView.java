package com.bwie.mvp.view;

import com.bwie.base.BaseView;
import com.bwie.my.bean.FenBean;
import com.bwie.my.bean.HomeBean;

/**
 * Created by wangbingjun on 2018/11/13.
 */

public interface MainIView extends BaseView {
    void onSuccess(HomeBean.DataBean homeBean);
    void onYouSuccess(FenBean fenBean);
    void onError(String msg);
}