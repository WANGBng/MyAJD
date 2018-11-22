package com.bwie.mvp.presenter;

import com.bwie.base.BasePresenter;
import com.bwie.mvp.view.MainIView;
import com.bwie.my.bean.FenBean;
import com.bwie.my.utils.HttpUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wangbingjun on 2018/11/13.
 */

public class FenPresenter extends BasePresenter<MainIView> {
    public  void fenData(String cid){
        Observable<FenBean> data = HttpUtils.getdatanet().api.fenData(cid);
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FenBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FenBean fenBean) {
                        getView().onYouSuccess(fenBean);
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
