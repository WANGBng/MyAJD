package com.bwie.my.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.bwie.mvp.presenter.CartPresenter;
import com.bwie.mvp.presenter.JiaGouPresenter;
import com.bwie.mvp.view.InfoView;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.R;
import com.bwie.mvp.presenter.InfoPresenter;
import com.bwie.mvp.view.JiaView;
import com.bwie.my.adapter.DetailsAdapter;
import com.bwie.my.bean.GoodsInfoBean;
import com.bwie.my.bean.JiaGouBean;
import com.bwie.my.fragment.ClassifyFragment;
import com.bwie.my.fragment.DianPuFragment;
import com.bwie.my.fragment.GouWuChe_Fragment;
import com.bwie.my.fragment.HomePager_Fragment;
import com.bwie.my.fragment.KefuFragment;
import com.bwie.my.fragment.MessageFragment;
import com.bwie.my.fragment.My__Fragment;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hjm.bottomtabbar.BottomTabBar;
import com.recker.flybanner.FlyBanner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InfoActivity extends AppCompatActivity implements InfoView,JiaView {
    private static final String TAG = "InfoActivity";
    @BindView(R.id.info_simple)
    SimpleDraweeView simple;
    @BindView(R.id.flyBanner)
    ViewPager viewPager;
  /*  @BindView(R.id.flybanner)
    ViewPager viewpager;*/
    @BindView(R.id.gwc)
    TextView gwc;
    @BindView(R.id.jrgwc)
    TextView jrgwc;
    @BindView(R.id.ljgm)
    TextView ljgm;
    @BindView(R.id.txt_fen)
    TextView textFen;
    @BindView(R.id.txt_price)
    TextView txtPrice;
    @BindView(R.id.txt_subhead)
    TextView textSubhead;
    @BindView(R.id.txt_salenum)
    TextView salenum;
    private InfoPresenter infoPresenter;
    private CartPresenter cartPresenter;
    private JiaGouPresenter jiaGouPresenter;
    private SharedPreferences sharedPreferences;
    String uid;
    int pid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);
        infoPresenter = new InfoPresenter();
        infoPresenter.attachView(this);

        int pid = getIntent().getIntExtra("pid", 1);
        infoPresenter.loadData(pid);

        //就如缓存并且加入到购物车
        sharedPreferences = getSharedPreferences("flag",Context.MODE_PRIVATE);
        uid = sharedPreferences.getString("uid","1");

        jiaGouPresenter = new JiaGouPresenter();
        jiaGouPresenter.attachView(this);

    }

    @Override
    public void onSuccess(GoodsInfoBean goodsInfoBean) {
        txtPrice.setText( "￥"+goodsInfoBean.getData().getPrice() );
        textFen.setText("￥"+goodsInfoBean.getData().getBargainPrice());
        txtPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        textSubhead.setText(goodsInfoBean.getData().getTitle());
        salenum.setText(String.valueOf(goodsInfoBean.getData().getSalenum()));

        String images = goodsInfoBean.getData().getImages();
        String[] split = images.split("\\|");

        DetailsAdapter adapter = new DetailsAdapter(InfoActivity.this, split);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onError(String msg) {

    }

    @OnClick({R.id.gwc,R.id.jrgwc,R.id.ljgm})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.gwc:
               // view = View.inflate(context,R.layout.gouwuche_layout,null);
                /*Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);*/
               // return view;
                Toast.makeText(this," 目前不支持",Toast.LENGTH_SHORT).show();
                break;
            case R.id.jrgwc:
                if (uid != null && "1".equals(uid)){
                    jiaGouPresenter.getJiaGou(uid,String.valueOf(pid));
                    Toast.makeText(this,"加入是加入成功了，你有钱买么？",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"你登录了么?没登录就去登陆",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ljgm:
                Toast.makeText(this,"你没钱，别买了",Toast.LENGTH_SHORT).show();
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (infoPresenter!=null || jiaGouPresenter!=null ){
            infoPresenter.detachView();
            jiaGouPresenter.detachView();
        }
    }

    @Override
    public void onJiaSuccess(JiaGouBean jiaGouBean) {
        if (jiaGouBean.getMsg().equals("加购成功")){
            Toast.makeText(this,"加购成功",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onJiaError(Throwable t) {

    }
}
