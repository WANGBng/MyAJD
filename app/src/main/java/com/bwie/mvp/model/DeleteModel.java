package com.bwie.mvp.model;

import com.bwie.my.bean.DeleteBean;
import com.bwie.my.utils.HttpUtils;

import io.reactivex.Observable;

/**
 * Created by wangbingjun on 2018/11/20.
 */

public class DeleteModel {
    public Observable<DeleteBean> getDel(String uid,String pid){
        Observable<DeleteBean> delete = HttpUtils.getdatanet().api.delete(uid, pid);
        return delete;
    }
}
