package com.bwie.my.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.R;
import com.bwie.my.bean.CartBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by wangbingjun on 2018/11/19.
 */

public class ShopperAdapter extends RecyclerView.Adapter<ShopperAdapter.ViewHolder> {
    private Context context;
    private List<CartBean.DataBean> list;
    private ShopperrAdapter shopperrAdapter;

    public ShopperAdapter(Context context, List<CartBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.gwc_shangjia_item,null));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.gwc_checkBox.setChecked(list.get(position).isOutchecked());
        holder.gwc_checkBox.setText(list.get(position).getSellerName());
        /*String images = list.get(position).getList().get(position).getImages();
        String[] split = images.split("\\|");*/
        holder.gwc_recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        final List<CartBean.DataBean.ListBean> listBeans = this.list.get(position).getList();

        shopperrAdapter = new ShopperrAdapter(context,listBeans);
        holder.gwc_recyclerView.setAdapter(shopperrAdapter);
        holder.gwc_checkBox.setOnClickListener(null);
        holder.gwc_checkBox.setOnClickListener(new View.OnClickListener() {
            @Override//shangjian商家的条目
            public void onClick(View v) {
                boolean isChecked = holder.gwc_checkBox.isChecked();
                list.get(position).setOutchecked(isChecked);
                if (isChecked){
                    for (int i = 0; i < list.get(holder.getLayoutPosition()).getList().size(); i++) {
                        list.get(holder.getLayoutPosition()).getList().get(i).setInnerchecked(true);
                    }
                }else {
                    for (int i = 0; i < list.get(holder.getLayoutPosition()).getList().size(); i++) {
                        list.get(holder.getLayoutPosition()).getList().get(i).setInnerchecked(false);
                    }
                }
                onClickChangeListener.onChecked(holder.getLayoutPosition(),isChecked);
            }
        });
        shopperrAdapter.setonClickChangeListener(new ShopperrAdapter.onClickChangeListener() {
            @Override
            public void onChecked(int layoutPosition, boolean checked) {
                boolean byt = true;
                for (int i = 0; i < list.size(); i++) {
                    boolean innerChecked = list.get(holder.getLayoutPosition()).getList().get(i).isInnerchecked();
                    byt = (byt&innerChecked);
                }
                holder.gwc_checkBox.setChecked(byt);
                list.get(position).setOutchecked(byt);
                onClickChangeListener.onChecked(holder.getLayoutPosition(),checked);
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox gwc_checkBox;
        RecyclerView gwc_recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            gwc_checkBox = itemView.findViewById(R.id.gwc_simple);
            gwc_recyclerView = itemView.findViewById(R.id.recyclerViewGouWuChe);
        }
    }
    private OnClickChangeListener onClickChangeListener;
    //向外面提供方法
    public void setOnClickChangeListener(ShopperAdapter.OnClickChangeListener onClickChangeListener) {
        this.onClickChangeListener = onClickChangeListener;
    }
    //全选的商家回调方法
    public  interface OnClickChangeListener{
        void onChecked(int layoutPosition,boolean checked);
        void onItemChecked(int layoutPosition,boolean ischecked);
    }
}
