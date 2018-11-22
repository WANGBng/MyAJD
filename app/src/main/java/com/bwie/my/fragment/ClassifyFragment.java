package com.bwie.my.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwie.R;
import com.bwie.mvp.presenter.FenPresenter;
import com.bwie.mvp.presenter.MainPresenter;
import com.bwie.mvp.view.MainIView;
import com.bwie.my.activity.SouActivity;
import com.bwie.my.adapter.FYAdapter;
import com.bwie.my.adapter.FZAdapter;
import com.bwie.my.bean.FenBean;
import com.bwie.my.bean.HomeBean;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by wangbingjun on 2018/10/18.
 */

public class ClassifyFragment extends Fragment implements MainIView {
    private static final int REQUEST_CODE = 1000;
    private View view;
    @BindView(R.id.sao)
    ImageView sao;
    @BindView(R.id.sou)
    ImageView sou;
    @BindView(R.id.fen_edit_sou)
    EditText editSou;
    @BindView(R.id.recl_left)
    RecyclerView reclLeft;
    @BindView(R.id.recl_right)
    RecyclerView reclRight;
    Unbinder unbinder;
    private FenPresenter fenPresenter;
    private MainPresenter mainPresenter;
    private FZAdapter fzAdapter;
    private FYAdapter fyAdapter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fenlei_layout,container,false);
        fenPresenter = new FenPresenter();
        fenPresenter.attachView(this);
        fenPresenter.fenData("1");
        mainPresenter = new MainPresenter();
        mainPresenter.loadData();
        mainPresenter.attachView(this);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @OnClick({R.id.sao,R.id.sou})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.sao:
                Intent saointent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(saointent,REQUEST_CODE);
                break;
            case R.id.sou:
                Intent souintent = new Intent(getActivity(), SouActivity.class);
                startActivity(souintent);
                break;
        }
    }
    @Override
    public void onSuccess(HomeBean.DataBean homeBean) {

        //分类左边
        final List<HomeBean.DataBean.FenleiBean> fenlei = homeBean.getFenlei();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        reclLeft.setLayoutManager(linearLayoutManager);
        fzAdapter = new FZAdapter(getActivity(),fenlei);
        reclLeft.setAdapter(fzAdapter);
        fzAdapter.setOnItemFenLeiClickListener(new FZAdapter.OnItemFenLeiClickListener() {
            @Override//点击监听时将id传入
            public void onItemFenLeiClick(int position) {
                fenPresenter.fenData(String.valueOf(fenlei.get(position).getCid()));
            }
        });//分类左边
    }

    @Override
    public void onYouSuccess(FenBean fenBean) {

        List<FenBean.DataBean> fenData = fenBean.getData();

        GridLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(),1,GridLayoutManager.VERTICAL,false);
        reclRight.setLayoutManager(linearLayoutManager);

        fyAdapter = new FYAdapter(getActivity(),fenData);
        reclRight.setAdapter(fyAdapter);

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
                /*if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    //用默认浏览器打开扫描得到的地址
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(result.toString());
                    intent.setData(content_url);
                    startActivity(intent);

                } else if (bundle.getInt(CodeUtils.RESULT_TYPE)
                        == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(),"解析二维码失败", Toast.LENGTH_LONG).show();
                }*/
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fenPresenter.detachView();
        mainPresenter.detachView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
