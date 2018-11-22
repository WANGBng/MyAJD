package com.bwie.my.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.R;
import com.bwie.mvp.presenter.CartPresenter;
import com.bwie.mvp.view.CartView;
import com.bwie.my.adapter.ShopperAdapter;
import com.bwie.my.bean.BeanB;
import com.bwie.my.bean.CartBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by wangbingjun on 2018/10/18.
 */

public class GouWuChe_Fragment extends Fragment implements CartView {
    private View view;
    @BindView(R.id.recyclerViewGouWuChe)
    RecyclerView recCar;
    @BindView(R.id.cb_total_select)
    CheckBox checkbox;
    @BindView(R.id.heJi)
    TextView zj;
    Unbinder unbinder;
    private CartPresenter cartPresenter;
    private SharedPreferences sp;
    private String uid;
    private String token;
    private ShopperAdapter adapter;
    private List<CartBean.DataBean> data;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.gouwuche_layout,container,false);
        EventBus.getDefault().register(this);//注册eventbus–
        cartPresenter = new CartPresenter();
        cartPresenter.attachView(this);
        unbinder = ButterKnife.bind(this, view);
        sp = getActivity().getSharedPreferences("flag", Context.MODE_PRIVATE);
        uid = sp.getString("uid", "1");
        token = sp.getString("token", "");
        cartPresenter.getCart(uid,token);

        return view;
    }

    @Override
    public void onCarSuccess(final CartBean carBean) {
        data=carBean.getData();
        if (data!=null&&!"1".equals(uid)){
            recCar.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
            adapter = new ShopperAdapter(getActivity(),data);
            recCar.setAdapter(adapter);
            //全选 来控制商家跟条目
            adapter.setOnClickChangeListener(new ShopperAdapter.OnClickChangeListener() {
                @Override
                public void onChecked(int layoutPosition, boolean checked) {
                    boolean b=true;
                    for (int i = 0; i < carBean.getData().size(); i++) {
                        boolean outchecked = carBean.getData().get(i).isOutchecked();
                        for (int j = 0; j < carBean.getData().get(i).getList().size(); j++) {
                            boolean innerchecked = carBean.getData().get(i).getList().get(j).isInnerchecked();
                            b=(b&outchecked&innerchecked);
                        }
                    }
                    checkbox.setChecked(b);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onItemChecked(int layoutPosition, boolean ischecked) {
                    //设置外层的选中状态
                    carBean.getData().get(layoutPosition).setOutchecked(ischecked);
                    boolean b=true;
                    for (int i = 0; i < carBean.getData().size(); i++) {
                        boolean outchecked = carBean.getData().get(i).isOutchecked();
                        for (int j = 0; j < carBean.getData().get(i).getList().size(); j++) {
                            boolean innerchecked = carBean.getData().get(i).getList().get(j).isInnerchecked();
                            b=(b&outchecked&innerchecked);
                        }
                    }
                    checkbox.setChecked(b);
                    adapter.notifyDataSetChanged();
                }
            });
        }
//全选的点击按钮
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox.isChecked()){
                    for (int i = 0; i < carBean.getData().size(); i++) {
                        carBean.getData().get(i).setOutchecked(true);
                        for (int j = 0; j < carBean.getData().get(i).getList().size(); j++) {
                            carBean.getData().get(i).getList().get(j).setInnerchecked(true);
                        }
                    }
                } else {
                    for (int i = 0; i < carBean.getData().size(); i++) {
                        carBean.getData().get(i).setOutchecked(false);
                        for (int j = 0; j < carBean.getData().get(i).getList().size(); j++) {
                            carBean.getData().get(i).getList().get(j).setInnerchecked(false);
                        }
                    }
                }
                initzong();
                adapter.notifyDataSetChanged();
            }
        });
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void  msg(BeanB ha){
        initzong();
    }
    private void initzong() {
        int zong=0;
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).getList().size(); j++) {
                if (data.get(i).getList().get(j).isInnerchecked()){
                    zong+=data.get(i).getList().get(j).getNum()*data.get(i).getList().get(j).getPrice();
                }
            }
        }
        zj.setText("合计：￥"+zong+"元");
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCarError(Throwable t) {

    }

    @Override
    public void onResume() {
        super.onResume();
        cartPresenter.getCart(uid,token);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cartPresenter.detachView();
        EventBus.getDefault().unregister(this);
    }
}
