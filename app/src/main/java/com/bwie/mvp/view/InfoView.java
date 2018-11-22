package com.bwie.mvp.view;

import com.bwie.base.BaseView;
import com.bwie.my.bean.GoodsInfoBean;

/**
 * Created by wangbingjun on 2018/11/13.
 */

public interface InfoView extends BaseView {
    void onSuccess(GoodsInfoBean goodsInfoBean);
    void onError(String msg);
}
