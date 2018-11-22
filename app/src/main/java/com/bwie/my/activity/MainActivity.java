package com.bwie.my.activity;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.bwie.R;
import com.bwie.my.fragment.ClassifyFragment;
import com.bwie.my.fragment.GouWuChe_Fragment;
import com.bwie.my.fragment.HomePager_Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.bwie.my.fragment.MessageFragment;
import com.bwie.my.fragment.My__Fragment;
import com.hjm.bottomtabbar.BottomTabBar;
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //全屏沉浸式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initDataDiBu();
    }
    //底部导航
    private void initDataDiBu() {
        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(50, 50)
                .setFontSize(10)
                .setTabPadding(1, 6, 10)
                .setChangeColor(Color.RED, Color.DKGRAY)
                .addTabItem("首页", R.drawable.home, HomePager_Fragment.class)
                .addTabItem("分类", R.drawable.classily, ClassifyFragment.class)
                .addTabItem("消息", R.drawable.sou, MessageFragment.class)
                .addTabItem("购物车", R.drawable.shop, GouWuChe_Fragment.class)
                .addTabItem("我的", R.drawable.ac2, My__Fragment.class)
                .setTabBarBackgroundColor(Color.WHITE)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name, View view) {

                        Log.e(TAG, "位置: "+position+"选项卡的名字;"+name);



                    }
                })
                .isShowDivider(false);
    }

}