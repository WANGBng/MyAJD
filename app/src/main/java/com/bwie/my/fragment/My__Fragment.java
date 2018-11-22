package com.bwie.my.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.R;
import com.bwie.mvp.presenter.MainPresenter;
import com.bwie.mvp.view.MainIView;
import com.bwie.my.activity.LoginActivity;
import com.bwie.my.adapter.TuiAdapter;
import com.bwie.my.bean.FenBean;
import com.bwie.my.bean.HomeBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by wangbingjun on 2018/10/18.
 */
//我的页面
public class My__Fragment extends Fragment implements MainIView {

    @BindView(R.id.simpleView_img)
    SimpleDraweeView simpleView_img;

    @BindView(R.id.dengL)
    TextView dengL;

    @BindView(R.id.rv_tui_jian_my)//我的里面的为你推荐
     RecyclerView rv_tui_jian_my;

    Unbinder unbinder;
    TuiAdapter tad;
    private View view;
    private MainPresenter mainPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.wode_layout,container,false);
        unbinder = ButterKnife.bind(this, view);
        mainPresenter = new MainPresenter();
        mainPresenter.loadData();
        mainPresenter.attachView(this);

        return view;
    }

    @Override//回调登陆的姓名等
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode ==999 && requestCode ==1){
            String mobile = data.getExtras().getString("mobile");
            dengL.setText(mobile);
        }else if (requestCode == 999 && requestCode == 888){
            String name = data.getExtras().getString("name");
            String iconurl = data.getExtras().getString("iconurl");//获取图像
            dengL.setText(name);

            Uri parse = Uri.parse(iconurl);
            simpleView_img.setImageURI(parse);
        }
    }

    @Override
    public void onSuccess(HomeBean.DataBean homeBean) {

        List<HomeBean.DataBean.TuijianBean.ListBeanX> list = homeBean.getTuijian().getList();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);
        rv_tui_jian_my.setLayoutManager(gridLayoutManager);
        tad = new TuiAdapter(getActivity(),list);
        rv_tui_jian_my.setAdapter(tad);

    }

    @Override
    public void onYouSuccess(FenBean fenBean) {

    }

    @Override
    public void onError(String msg) {

    }

    @OnClick({R.id.simpleView_img,R.id.dengL})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.simpleView_img:

                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.dengL:
                Intent intent1 = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
