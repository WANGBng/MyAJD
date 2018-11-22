package com.bwie.my.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.R;
import com.bwie.mvp.presenter.CartPresenter;
import com.bwie.mvp.presenter.DeletePresenter;
import com.bwie.mvp.view.DeleteIView;
import com.bwie.my.bean.BeanB;
import com.bwie.my.bean.CartBean;
import com.bwie.my.bean.DeleteBean;
import com.bwie.my.utils.ButtonT;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by wangbingjun on 2018/11/20.
 */

public class ShopperrAdapter extends RecyclerView.Adapter<ShopperrAdapter.ViewHolder> implements DeleteIView {
    private Context context;
    private List<CartBean.DataBean.ListBean> listBean;

    public ShopperrAdapter(Context context, List<CartBean.DataBean.ListBean> listBean) {
        this.context = context;
        this.listBean = listBean;
    }
    private DeletePresenter deletePresenter;
    private SharedPreferences sp;
    private String uid;
    private int pid;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.gwc_shangjia_shangpin_item, null);
        ViewHolder holder = new ViewHolder(view);
        deletePresenter = new DeletePresenter();
        deletePresenter.attachView(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Uri uri = Uri.parse(listBean.get(position).getImages().split("\\|")[0]);
        holder.simple_img.setImageURI(uri);
        holder.price.setText("￥"+listBean.get(position).getPrice());
        holder.shop_name.setText(listBean.get(position).getTitle());
        holder.text_num.setText(listBean.get(position).getNum()+"");

        holder.delete_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp = context.getSharedPreferences("flage", Context.MODE_PRIVATE);
                uid = sp.getString("uid","1");
                pid = listBean.get(position).getPid();
                deletePresenter.getDe(uid,String.valueOf(pid));
            }
        });
        holder.button.setAddAndMinusu(new ButtonT.AddAndMinus() {
            @Override
            public void add() {
                listBean.get(position).setNum(listBean.get(position).getNum()+1);
                BeanB beanB = new BeanB();
                EventBus.getDefault().post(beanB);

            }

            @Override
            public void minus() {
                listBean.get(position).setNum(listBean.get(position).getNum()-1);
                BeanB beanB = new BeanB();
                EventBus.getDefault().post(beanB);
            }
        });
        holder.check_zi.setChecked(listBean.get(position).isInnerchecked());
        holder.check_zi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listBean.get(position).setInnerchecked(holder.check_zi.isChecked());
                onClickChangeListener.onChecked(holder.getLayoutPosition(),holder.check_zi.isChecked());


            }
        });

    }

    @Override
    public int getItemCount() {
        return listBean.size();
    }

    @Override
    public void onDelSuccess(DeleteBean deleteBean) {
        Toast.makeText(context, ""+deleteBean.getMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDelError(DeleteBean deleteBean) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox check_zi;
        private final SimpleDraweeView simple_img;
        private final TextView shop_name;
        private final TextView price;
        private final ImageView delete_img;
        private final ButtonT button;
        private final TextView text_num;
        public ViewHolder(View itemView) {
            super(itemView);
            check_zi = (CheckBox) itemView.findViewById(R.id.check_zi);
            simple_img = (SimpleDraweeView) itemView.findViewById(R.id.simple_img);
            shop_name = (TextView) itemView.findViewById(R.id.shop_name);
            price = (TextView) itemView.findViewById(R.id.price);
            delete_img = (ImageView) itemView.findViewById(R.id.delete);
            button =  itemView.findViewById(R.id.butto);
            text_num = (TextView) itemView.findViewById(R.id.text_num);

        }
    }
    private onClickChangeListener onClickChangeListener;
    //向外提供的方法
    public void setonClickChangeListener(onClickChangeListener onClickChangeListener) {
        this.onClickChangeListener = onClickChangeListener;
    }

    //条目回调方法
    public  interface onClickChangeListener{
        void onChecked(int layoutPosition,boolean checked);
    }

}
