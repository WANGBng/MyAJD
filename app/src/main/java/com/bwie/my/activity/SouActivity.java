package com.bwie.my.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.R;
import com.bwie.app.LiuShi;
import com.bwie.mvp.view.MainIView;
import com.bwie.mvp.view.SouView;
import com.bwie.my.adapter.SouAdapter;
import com.bwie.my.bean.SouBean;
import com.bwie.my.utils.CustomView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SouActivity extends AppCompatActivity {

    @BindView(R.id.sou)
    ImageView sou;
    @BindView(R.id.sou_edit_sou)
    EditText sou_edit_sou;
    /*@BindView(R.id.liuShi)
    LiuShi liuShi;*/
    @BindView(R.id.customView)
    CustomView liuShi;
    private ViewGroup.MarginLayoutParams layoutParams;
    private List<String> list;
    private String string;
    private TextView textView;
    private SouAdapter souAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sou);
        ButterKnife.bind(this);
        sou_edit_sou.setCursorVisible(true);
        sou_edit_sou.setFocusable(true);
        sou_edit_sou.setFocusableInTouchMode(true);
        initDat();//定义间距
    }

    private void initDat() {
        layoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin =40;
        layoutParams.rightMargin = 40;
        list = new ArrayList<>();
    }

    @OnClick(R.id.sou)
    public void onViewClicked(){
        liuShi.removeAllViews();//将原有的视图先去除掉
        string = sou_edit_sou.getText().toString();
        list.add(string);
        for (int i = list.size()-1;i > -1; i--) {

            textView = new TextView(SouActivity.this);
            textView.setText(list.get(i));
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(layoutParams);//将上面定义的间距添加进
            liuShi.addView(textView);
        }
        if (string.length()>0){
            //将搜索的值传给展示视图也就是传给ShowActivity
            Intent intent = new Intent(SouActivity.this,ShowActivity.class);
            intent.putExtra("sousuo",string);
            startActivity(intent);
        }else if (string==null){

            Toast.makeText(this,"不能是空的",Toast.LENGTH_SHORT).show();
        }
//        Toast.makeText(this,"不能是空的:::"+string,Toast.LENGTH_SHORT).show();
    }
}
