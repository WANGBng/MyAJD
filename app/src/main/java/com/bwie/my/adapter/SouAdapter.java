package com.bwie.my.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.R;
import com.bwie.my.activity.InfoActivity;

import com.bwie.my.bean.SouBean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by wangbingjun on 2018/11/15.
 */

public class SouAdapter extends RecyclerView.Adapter<SouAdapter.ViewHolder>{
    private Context context;
    private List<SouBean.DataBean> sBean;

    public SouAdapter(Context context, List<SouBean.DataBean> sBean) {
        this.context = context;
        this.sBean = sBean;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.sou_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.show_souAdapterTitle.setText(sBean.get(position).getTitle());
        holder.show_souAdapterPrice.setText("￥"+String.valueOf(sBean.get(position).getPrice()+""));

        Uri uri = Uri.parse(sBean.get(position).getImages().split("\\|")[0]);//图片的切割
        AbstractDraweeController fresco = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .build();
        holder.show_adapterSimple.setController(fresco);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//点击时将pid传到商品的详情去
                Intent intent = new Intent(context, InfoActivity.class);
                int pid = sBean.get(position).getPid();
                intent.putExtra("pid",pid);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return sBean.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView show_souAdapterTitle;
        TextView show_souAdapterPrice;
        SimpleDraweeView show_adapterSimple;
        public ViewHolder(View itemView) {
            super(itemView);
            show_souAdapterTitle = (TextView) itemView.findViewById(R.id.show_souAdapterTitle);
            show_souAdapterPrice = (TextView) itemView.findViewById(R.id.show_souAdapterPrice);
            show_adapterSimple = (SimpleDraweeView) itemView.findViewById(R.id.show_adapterSimple);
        }
    }
}
