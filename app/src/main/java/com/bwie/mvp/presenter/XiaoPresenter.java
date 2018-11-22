package com.bwie.mvp.presenter;

import com.bwie.base.BasePresenter;
import com.bwie.mvp.view.XiaoView;
import com.bwie.my.bean.XiaoBean;
import com.bwie.my.utils.HttpUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wangbingjun on 2018/11/20.
 */

public class XiaoPresenter extends BasePresenter<XiaoView> {
    public void paiXu(String pscid,String sort){
        Observable<XiaoBean> sousuo = HttpUtils.getdatanet().api.xiao(pscid, sort);
        sousuo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<XiaoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(XiaoBean xiaoBean) {
                        getView().onSuccesss(xiaoBean);
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
