package com.bwie.mvp.presenter;

import android.util.Log;

import com.bwie.base.BasePresenter;
import com.bwie.mvp.model.CartModel;
import com.bwie.mvp.view.CartView;
import com.bwie.my.bean.CartBean;
import com.bwie.my.utils.HttpUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wangbingjun on 2018/11/19.
 */

public class CartPresenter extends BasePresenter<CartView> {

    private static final String TAG = "CartPresenter";
    private final CartModel cartModel;
    public CartPresenter(){
        cartModel = new CartModel();
    }
    public void getCart(String uid,String token){
        cartModel.getCartData(uid,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CartBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(CartBean cartBean) {
                        if (cartBean != null){
                            getView().onCarSuccess(cartBean);
                        }else {
                            Log.e(TAG, "cartBean:是空的 "+cartBean );
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