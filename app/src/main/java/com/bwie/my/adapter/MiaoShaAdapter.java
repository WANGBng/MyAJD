package com.bwie.my.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.R;
import com.bwie.my.bean.HomeBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by wangbingjun on 2018/11/13.
 */

public class MiaoShaAdapter extends RecyclerView.Adapter<MiaoShaAdapter.ViewHolder> {
    private Context context;
    private HomeBean.DataBean.MiaoshaBean data;

    public MiaoShaAdapter(Context context, HomeBean.DataBean.MiaoshaBean data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(View.inflate(context, R.layout.miaoitem,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Uri uri = Uri.parse(data.getList().get(position).getImages().split("\\|")[0]);
        holder.simple.setImageURI(uri);
        holder.text_fen.setText(data.getList().get(position).getPrice()+"￥");
    }

    @Override
    public int getItemCount() {
        return data.getList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_fen;
        SimpleDraweeView simple;
        public ViewHolder(View itemView) {
            super(itemView);
            text_fen = (TextView) itemView.findViewById(R.id.text_fenn);
            simple = (SimpleDraweeView) itemView.findViewById(R.id.simpleMiao);
        }
    }
}
