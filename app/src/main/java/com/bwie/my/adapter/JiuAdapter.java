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
//九宫格的适配器
public class JiuAdapter extends RecyclerView.Adapter<JiuAdapter.ViewHolder> {
    private Context context;
    private List<HomeBean.DataBean.FenleiBean> data;

    public JiuAdapter(Context context, List<HomeBean.DataBean.FenleiBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = View.inflate(context, R.layout.jiuitem,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.text_fen.setText(data.get(position).getName());

        Uri uri = Uri.parse(data.get(position).getIcon().split("\\|")[0]);
        holder.simple.setImageURI(uri);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_fen;
        SimpleDraweeView simple;

        public ViewHolder(View itemView) {
            super(itemView);
            text_fen = (TextView) itemView.findViewById(R.id.text_fen);
            simple = (SimpleDraweeView) itemView.findViewById(R.id.simple);
        }
    }
}
