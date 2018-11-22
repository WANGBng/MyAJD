package com.bwie.mvp.presenter;

import com.bwie.base.BasePresenter;
import com.bwie.mvp.model.MainModel;
import com.bwie.mvp.view.MainIView;
import com.bwie.my.bean.HomeBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wangbingjun on 2018/10/18.
 */

public class MainPresenter extends BasePresenter<MainIView> {

    private final MainModel mainModel;

    public MainPresenter() {
        mainModel = new MainModel();
    }

    public void loadData(){
        mainModel.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        HomeBean.DataBean data = homeBean.getData();
                        getView().onSuccess(data);
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
