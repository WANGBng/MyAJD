package com.bwie.mvp.view;

import com.bwie.base.BaseView;
import com.bwie.my.bean.DeleteBean;

/**
 * Created by wangbingjun on 2018/11/20.
 */

public interface DeleteIView extends BaseView {
    void onDelSuccess(DeleteBean deleteBean);
    void onDelError(DeleteBean deleteBean);
}
