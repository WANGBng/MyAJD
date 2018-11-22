package com.bwie.my.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.R;
import com.bwie.my.bean.FenBean;

import java.util.List;

/**
 * Created by wangbingjun on 2018/11/14.
 */

public class FYAdapter extends RecyclerView.Adapter<FYAdapter.ViewHolder> {

    private Context context;
    private List<FenBean.DataBean> dataBeans;

    public FYAdapter(Context context, List<FenBean.DataBean> dataBeans) {
        this.context = context;
        this.dataBeans = dataBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.fy_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.text_youFen.setText(dataBeans.get(position).getName());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false);
        ZAdapter zAdapter = new ZAdapter(context,dataBeans);
        holder.you_recycler.setLayoutManager(gridLayoutManager);
        holder.you_recycler.setAdapter(zAdapter);
    }

    @Override
    public int getItemCount() {
        if (dataBeans!=null){

            return dataBeans.size();
        }else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView text_youFen;
        RecyclerView you_recycler;
        public ViewHolder(View itemView) {
            super(itemView);
            text_youFen = (TextView) itemView.findViewById(R.id.text_youFen);
            you_recycler = itemView.findViewById(R.id.you_recycler);
        }
    }
}
