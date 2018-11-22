package com.bwie.my.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisActivity extends AppCompatActivity {

    @BindView(R.id.reg_mobile)
    EditText reg_mobile;
    @BindView(R.id.reg_password)
    EditText reg_password;
    @BindView(R.id.reg_reg)
    Button reg_reg;
    @BindView(R.id.lian_xi_ke_fu)
    Button lian_xi_ke_fu;
    @BindView(R.id.webView)
    WebView webView;
    WebView webView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);
        ButterKnife.bind(this);
        webView1 = new WebView(this);
       // webView.loadUrl("file:///android_asset/js_android.html");
        WebSettings webSettings = webView.getSettings();

        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new JSInterface(), "jsi");
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);


    }

    @OnClick({R.id.reg_reg,R.id.lian_xi_ke_fu})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.reg_reg:

                break;
            case R.id.lian_xi_ke_fu:

                webView.loadUrl("http://www.baidu.com");
//                setContentView(webView1);
                Toast.makeText(RegisActivity.this,"你点我了",Toast.LENGTH_SHORT).show();
                break;
        }
    }


    private class JSInterface {
        /**
         * 注意这里的@JavascriptInterface注解， target是4.2以上都需要添加这个注解，否则无法调用
         * @param text
         */
        @JavascriptInterface
        public void showToast(String text){
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        }
        @JavascriptInterface
        public void showJsText(String text){
            webView.loadUrl("javascript:jsText('"+text+"')");
        }
    }
}
