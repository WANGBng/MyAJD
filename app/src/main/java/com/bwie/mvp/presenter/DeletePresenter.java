package com.bwie.mvp.presenter;

import com.bwie.base.BasePresenter;
import com.bwie.mvp.model.DeleteModel;
import com.bwie.mvp.view.DeleteIView;
import com.bwie.my.bean.DeleteBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wangbingjun on 2018/11/20.
 */

public class DeletePresenter extends BasePresenter<DeleteIView>{

    private final DeleteModel deleteModel;
    public DeletePresenter(){
        deleteModel = new DeleteModel();
    }
    public void getDe(String uid,String pid){
        deleteModel.getDel(uid, pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DeleteBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DeleteBean deleteBean) {
                        if (deleteBean!=null){
                            getView().onDelSuccess(deleteBean);
                        }
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
