package com.bwie.my.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
 * Created by wangbingjun on 2018/11/20.
 */

public class SouSuoAdapter extends RecyclerView.Adapter<SouSuoAdapter.ViewHolder> {
    private Context context;
    private List<SouBean.DataBean> soulist;

    public SouSuoAdapter(Context context, List<SouBean.DataBean> soulist) {
        this.context = context;
        this.soulist = soulist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.showlayout,parent,false);
        ViewHolder myViewHolder = new ViewHolder (view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.textView_selanum.setText("销量:"+soulist.get(position).getSalenum());
        holder.textView_title.setText(soulist.get(position).getTitle());
        holder.textView_price.setText("价格:"+soulist.get(position).getPrice());
        String images = soulist.get(position).getImages();
        String[] split = images.split("\\|");
        Uri parse = Uri.parse(split[0]);
        AbstractDraweeController fresco =Fresco.newDraweeControllerBuilder()
                .setUri(parse)
                .build();
        holder.simpleDraweeView.setController(fresco);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InfoActivity.class);
                int pid = soulist.get(position).getPid();
                intent.putExtra("pid",pid);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return soulist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView_title;
        TextView textView_price;
        TextView textView_selanum;
        SimpleDraweeView simpleDraweeView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView_title = (TextView) itemView.findViewById(R.id.show_adapter_title);
            textView_price = (TextView) itemView.findViewById(R.id.show_adapter_price);
            textView_selanum = (TextView) itemView.findViewById(R.id.show_adapter_selanum);
            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.show_adapter_simple);
        }
    }
}