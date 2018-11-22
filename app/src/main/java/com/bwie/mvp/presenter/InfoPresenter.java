package com.bwie.mvp.presenter;

import android.util.Log;

import com.bwie.base.BasePresenter;
import com.bwie.mvp.view.InfoView;
import com.bwie.my.bean.GoodsInfoBean;
import com.bwie.my.utils.HttpUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wangbingjun on 2018/11/13.
 */

public class InfoPresenter extends BasePresenter<InfoView> {

    public void loadData(int pid){
        HttpUtils.getdatanet().api.queryGoodsByPid(pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GoodsInfoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GoodsInfoBean goodsInfoBean) {
                        getView().onSuccess(goodsInfoBean);
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
