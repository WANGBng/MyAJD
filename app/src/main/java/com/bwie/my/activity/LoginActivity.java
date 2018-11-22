package com.bwie.my.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.R;
import com.bwie.mvp.presenter.LoginPresenter;
import com.bwie.mvp.view.LoginView;
import com.bwie.my.bean.LoginBean;
import com.bwie.my.bean.RegisBean;
import com.bwie.my.fragment.My__Fragment;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {
    private static final String TAG = "LoginActivity";
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.edit_pwd)
    EditText editPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.login_qq)
    ImageView qq;
    @BindView(R.id.login_winXin)
    ImageView login_winXin;
    @BindView(R.id.btn_reg)
    TextView btnReg;
    @BindView(R.id.jing_dong_yin_si)
    TextView jing_dong_yin_si;
    @BindView(R.id.webView)
    WebView webView;
    //友盟的api
    private UMShareAPI umShareAPI;
//    登陆的东西
    private LoginPresenter loginPresenter;
    private String name1;
      String iconurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter();
        loginPresenter.attachView(this);
        WebView webView1;
        webView1 = new WebView(this);
        // webView.loadUrl("file:///android_asset/js_android.html");
        WebSettings webSettings = webView.getSettings();

        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new JSInterface(), "jsi");
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    }

    @Override//成功后进入这里
    public void onLoginSuccess(LoginBean loginBean) {
        String code = loginBean.getCode();
        String msg = loginBean.getMsg();

        if (code.equals("0")){
            String mobile = loginBean.getData().getMobile();
            Toast.makeText(this,"mobile："+msg,Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("mobile",mobile);
            intent.putExtras(bundle);
            setResult(1,intent);

            finish();
        }
    }

    @Override
    public void onRegSuccess(RegisBean regisBean) {

    }

    @Override
    public void onError(LoginBean loginBean) {
        Toast.makeText(this,"登陆有问题："+loginBean.getMsg(),Toast.LENGTH_SHORT).show();
        Log.e(TAG,"登陆有问题"+loginBean.getMsg());
    }

    @Override
    public void onErrorReg(RegisBean regisBean) {

    }

    @OnClick({R.id.btn_login,R.id.login_qq,R.id.btn_reg,R.id.login_winXin,R.id.jing_dong_yin_si})
    public void viewClicked(View view){
        switch (view.getId()){
            case R.id.btn_login:
                final String edName = editName.getText().toString();
                String edPwd = editPwd.getText().toString();
                loginPresenter.loginData(edName,edPwd);

                break;

            case R.id.login_qq:
                umShareAPI = UMShareAPI.get(this);

                UMAuthListener authListener = new UMAuthListener() {
                    /**
                     * @desc 授权开始的回调
                     * @param platform 平台名称
                     */
                    @Override
                    public void onStart(SHARE_MEDIA platform) {

                    }

                    /**
                     * @desc 授权成功的回调
                     * @param platform 平台名称
                     * @param action 行为序号，开发者用不上
                     * @param data 用户资料返回
                     */
                    @Override
                    public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
                        Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();

                        Toast.makeText(LoginActivity.this, "授权成功后的回调数据，用户信息"+data.get("name"), Toast.LENGTH_LONG).show();

                        name1 =data.get("name");
                        iconurl =data.get("iconurl");
                        /*Intent intent2 = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putString("name",name1);
                        bundle.putString("iconurl",iconurl);
                        intent2.putExtras(bundle);
                        setResult(888,intent2);*/
                        finish();

                    }

                    /**
                     * @desc 授权失败的回调
                     * @param platform 平台名称
                     * @param action 行为序号，开发者用不上
                     * @param t 错误原因
                     */
                    @Override
                    public void onError(SHARE_MEDIA platform, int action, Throwable t) {

                        Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(),Toast.LENGTH_LONG).show();
                    }

                    /**
                     * @desc 授权取消的回调
                     * @param platform 平台名称
                     * @param action 行为序号，开发者用不上
                     */
                    @Override
                    public void onCancel(SHARE_MEDIA platform, int action) {
                        Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
                    }
                };
                umShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, authListener);

                break;

            case R.id.btn_reg:
            Toast.makeText(this,"前往注册",Toast.LENGTH_SHORT).show();
            Intent regIntent = new Intent(LoginActivity.this,RegisActivity.class);
            startActivity(regIntent);
            break;
            case R.id.login_winXin:
                Toast.makeText(this,"对不起暂时不支持微信登陆",Toast.LENGTH_SHORT).show();
                break;
            case R.id.jing_dong_yin_si:
                webView.loadUrl("http://www.baidu.com");
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.detachView();
    }
}
