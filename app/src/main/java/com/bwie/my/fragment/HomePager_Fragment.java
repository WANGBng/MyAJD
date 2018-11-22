package com.bwie.my.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.R;
import com.bwie.mvp.presenter.MainPresenter;
import com.bwie.mvp.view.MainIView;
import com.bwie.my.activity.SouActivity;
import com.bwie.my.adapter.JiuAdapter;
import com.bwie.my.adapter.MiaoShaAdapter;
import com.bwie.my.adapter.TuiAdapter;
import com.bwie.my.bean.FenBean;
import com.bwie.my.bean.HomeBean;
import com.recker.flybanner.FlyBanner;
import com.sunfusheng.marqueeview.MarqueeView;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by wangbingjun on 2018/10/18.
 */

public class HomePager_Fragment extends Fragment implements MainIView {
    private View view;

    //    private String bannerUrl = "https://www.zhaoapi.cn/ad/getAd";
     private static final int REQUEST_CODE = 1000;
    private static final String TAG = "HomePager_Fragment:=+";
    @BindView(R.id.fly_banner)
    FlyBanner flyBanner;
    Unbinder unbinder;
    @BindView(R.id.rec_jiu)
    RecyclerView recJiu;
    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;
    @BindView(R.id.tv_hour)
    TextView tvHour;
    @BindView(R.id.tv_minute)
    TextView tvMinute;
    @BindView(R.id.tv_second)
    TextView tvSecond;
    @BindView(R.id.dian)
    TextView dian;
    @BindView(R.id.miaosha)
    RecyclerView miaoshas;
    @BindView(R.id.tjRec)
    RecyclerView tjRec;
    @BindView(R.id.sao)
    ImageView sao;
    @BindView(R.id.sou)
    ImageView sou;
    @BindView(R.id.edit_sou)
    EditText editSou;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    private int h;
    private JiuAdapter jiuAdapter;
    private MiaoShaAdapter miaoShaAdapter;
    private TuiAdapter tuiAdapter;
    private MainPresenter mainPresenter;
    private List<String> images = new ArrayList<>();
    @BindView(R.id.line)
    LinearLayout line;

    //这是轮播图的handler
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                Calendar instance = Calendar.getInstance();
                int hour = instance.get(instance.HOUR_OF_DAY);
                int tohour = 2;
                if (hour % 2 == 0) {
                    h = hour + tohour;
                } else {
                    h = hour - 1 + tohour;
                }
                int minute = instance.get(Calendar.MINUTE);
                int second = instance.get(Calendar.SECOND);
//        计算时差
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                try {
                    Date d1 = simpleDateFormat.parse(h + ":00:00");
                    Date d2 = simpleDateFormat.parse(hour + ":" + minute + ":" + second);
                    //Date   d2 = new   Date(System.currentTimeMillis());//你也可以获取当前时间
                    long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别
                    long hours = (diff / (1000 * 60 * 60));
                    long minutes = (diff - hours * (1000 * 60 * 60)) / (1000 * 60);
                    long seconds = (diff - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / 1000;
                    dian.setText(h + "点场");
                    tvHour.setText(hours + "");
                    tvMinute.setText(minutes + "");
                    tvSecond.setText(seconds + "");

                } catch (Exception e) {
                }
                handler.sendEmptyMessageDelayed(0, 1000);
            }
        }
    }; //上面是轮播图的handler


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.homepager_layout,container,false);
        //line.bringToFront();
        mainPresenter = new MainPresenter();
        mainPresenter.attachView(this);
        mainPresenter.loadData();
        unbinder = ButterKnife.bind(this, view);
        handler.sendEmptyMessageDelayed(0, 1000);
        //跑马灯
        setPaoMaDeng();

        scrollView = (ScrollView) view.findViewById(R.id.scrollView);

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                }
            });
        }*/

        return view;
    }

    private void setPaoMaDeng() {
        List<String> info = new ArrayList<>();
        info.add("北京八维研修学院欢迎你的加入");
        info.add("各位买家需要什么");
        info.add("各位商家双十一已就绪");
        info.add("是不是卡上莫有钱?");
        info.add("北京八维研修学院欢迎你的加入");
        info.add("北京八维研修学院欢迎你的加入");
        info.add("北京八维研修学院欢迎你的加入");
        info.add("北京八维研修学院欢迎你的加入");
        info.add("北京八维研修学院欢迎你的加入");
        info.add("北京八维研修学院欢迎你的加入");
        info.add("北京八维研修学院欢迎你的加入");
        marqueeView.startWithList(info);


    }

    @Override
    public void onSuccess(HomeBean.DataBean homeBean) {

        //轮播图
        List<HomeBean.DataBean.BannerBean> banner = homeBean.getBanner();
        for (int i = 0; i < banner.size(); i++) {
//            homeBean.getBanner().get(i).getIcon().replace("https","http");
            images.add(homeBean.getBanner().get(i).getIcon().replace("https","http").split("\\|")[0]);

        }
        flyBanner.setImagesUrl(images);
//      轮播图
//      九宫格
        List<HomeBean.DataBean.FenleiBean> fenLei = homeBean.getFenlei();
        GridLayoutManager jiugongge= new GridLayoutManager(getActivity(),2,GridLayoutManager.HORIZONTAL,false);
        recJiu.setLayoutManager(jiugongge);

        jiuAdapter = new JiuAdapter(getActivity(),fenLei);
        recJiu.setAdapter(jiuAdapter);
//      九宫格
//        秒杀
        HomeBean.DataBean.MiaoshaBean miaoSha = homeBean.getMiaosha();
        GridLayoutManager miaoGrid= new GridLayoutManager(getActivity(),1,GridLayoutManager.HORIZONTAL,false);
        miaoshas.setLayoutManager(miaoGrid);
        miaoShaAdapter = new MiaoShaAdapter(getActivity(), miaoSha);
        miaoshas.setAdapter(miaoShaAdapter);
//        秒杀
//        为你推荐
        final List<HomeBean.DataBean.TuijianBean.ListBeanX> list = homeBean.getTuijian().getList();
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        tjRec.setLayoutManager(gridLayoutManager);
        tuiAdapter = new TuiAdapter(getActivity(),list);
        tjRec.setAdapter(tuiAdapter);


//        为你推荐
    }

    @Override
    public void onYouSuccess(FenBean fenBean) {

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
                    return;}
                if (bundle.getInt(CodeUtils.RESULT_TYPE)
                        == CodeUtils.RESULT_SUCCESS) {
                    String result =
                            bundle.getString(CodeUtils.RESULT_STRING);
                    //用默认浏览器打开扫描得到的地址
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(result.toString());
                    intent.setData(content_url);
                    startActivity(intent);

                } else if (bundle.getInt(CodeUtils.RESULT_TYPE)
                        == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(),"解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick({R.id.sao, R.id.sou,R.id.edit_sou})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sao:
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.sou:
                Intent intent2 = new Intent(getActivity(), SouActivity.class);
                startActivity(intent2);
                break;
            case R.id.edit_sou:
                Intent intent3 = new Intent(getActivity(), SouActivity.class);
                startActivity(intent3);
                break;
            /*case R.id.scrollView:

                scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                    @Override
                    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                        float imageHeight=300;
                        if (scrollY <= 0) {
                            line.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或者美工提供
                        } else if (scrollY > 0 && scrollY <= imageHeight) {
                            float scale = (float) scrollY / imageHeight;
                            float alpha = (255 * scale);
                            // 只是layout背景透明
                            line.setBackgroundColor(Color.argb((int) alpha, 227, 29, 26));
                        } else {
                            line.setBackgroundColor(Color.argb((int) 255, 227, 29, 26));
                        }
                    }
                });


                break;*/
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
        unbinder.unbind();
    }
}