package com.bwie.my.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.bwie.R;

public class TimeActivity extends AppCompatActivity {

    private TextView settime;//倒计时的TextView
    //设定一个int
    private int time = 3;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                if (time>0){
                    time--;
                    handler.sendEmptyMessageDelayed (0,1000);
                }else {
                    Intent intent=new Intent (TimeActivity.this,MainActivity.class);
                    startActivity (intent);
                    finish ();
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //全屏沉浸式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        handler.sendEmptyMessageDelayed (0,1000);

    }

}
