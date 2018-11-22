package com.bwie.mvp.presenter;
import android.util.Log;
import com.bwie.base.BasePresenter;
import com.bwie.mvp.model.JiaModel;
import com.bwie.mvp.view.JiaView;
import com.bwie.my.bean.JiaGouBean;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
/**
 * Created by wangbingjun on 2018/11/20.
 */
public class JiaGouPresenter extends BasePresenter<JiaView> {
    private static final String TAG = "JiaGouPresenter";
    private final JiaModel jiaModel;
    public JiaGouPresenter(){
        jiaModel = new JiaModel();
    }
    public void getJiaGou(String uid,String pid){
        jiaModel.getJiGou(uid,pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JiaGouBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(JiaGouBean jiaGouBean) {
                        if (jiaGouBean != null){
                            getView().onJiaSuccess(jiaGouBean);
                        }else {
                            Log.e(TAG, "cartBean:是空的 "+jiaGouBean );
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