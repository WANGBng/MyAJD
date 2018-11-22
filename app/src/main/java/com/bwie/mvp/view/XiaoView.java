package com.bwie.mvp.view;

import com.bwie.base.BaseView;
import com.bwie.my.bean.XiaoBean;

/**
 * Created by wangbingjun on 2018/11/20.
 */

public interface XiaoView extends BaseView{
    void onSuccesss(XiaoBean xiaoBean);
    void onerrorr(XiaoBean xiaoBean);
}
