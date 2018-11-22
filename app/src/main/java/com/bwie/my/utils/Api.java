package com.bwie.my.utils;

import com.bwie.my.bean.CartBean;
import com.bwie.my.bean.DeleteBean;
import com.bwie.my.bean.FenBean;
import com.bwie.my.bean.GoodsInfoBean;
import com.bwie.my.bean.HomeBean;
import com.bwie.my.bean.JiaGouBean;
import com.bwie.my.bean.LoginBean;
import com.bwie.my.bean.MyBean;
import com.bwie.my.bean.RegisBean;
import com.bwie.my.bean.SouBean;
import com.bwie.my.bean.XiaoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wangbingjun on 2018/11/13.
 */

public interface Api {
    //首页
    @GET("home/getHome")
    Observable<HomeBean> shouye();
    //搜索
    @GET("product/searchProducts")
    Observable<SouBean> sousuo(@Query("keywords")String keywords, @Query("page")int page);
    //详情
    @GET("product/getProductDetail")
    Observable<GoodsInfoBean> queryGoodsByPid(@Query("pid") int pid);
    //右分类
    @GET("product/getProductCatagory")
    Observable<FenBean> fenData(@Query("cid")String cid);
    @GET("user/login")
    Observable<LoginBean> loginData(@Query("mobile")String mobile, @Query("password")String password);
    @GET("user/reg")
    Observable<RegisBean> RegData(@Query("mobile")String mobile, @Query("password")String password);
    //个人中心
    @GET("user/getUserInfo")
    Observable<MyBean> getUser(@Query("uid") String uid);

    //购物车
    @GET("product/getCarts")
    Observable<CartBean> getCar(@Query("uid") String uid, @Query("token") String token);
    //加入购物车
    @GET("product/addCart")
    Observable<JiaGouBean> jiaCar(@Query("uid") String uid, @Query("pid") String pid);
    //销量价格
    @GET("product/getProducts")
    Observable<XiaoBean> xiao(@Query("pscid") String pscid, @Query("sort") String sort);
    //删除购物车
    @GET("product/deleteCart")
    Observable<DeleteBean> delete(@Query("uid") String uid, @Query("pid") String pid);

}
