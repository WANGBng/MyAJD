package com.bwie.mvp.presenter;

import com.bwie.base.BasePresenter;
import com.bwie.mvp.view.GeView;
import com.bwie.my.bean.MyBean;
import com.bwie.my.utils.HttpUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wangbingjun on 2018/11/20.
 */

public class GeRenPresenter extends BasePresenter<GeView> {
    public void getDataa(String uid){
        //获取数据
        Observable<MyBean> user = HttpUtils.getdatanet().api.getUser(uid);
        user.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(MyBean geRenBean) {
                        getView().onSucce(geRenBean);
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }
}