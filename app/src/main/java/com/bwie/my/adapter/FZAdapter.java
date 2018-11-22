package com.bwie.my.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.R;
import com.bwie.my.bean.HomeBean;

import java.util.List;

/**
 * Created by wangbingjun on 2018/11/14.
 */

public class FZAdapter extends RecyclerView.Adapter<FZAdapter.ViewHolder> {
    private Context context;
    private List<HomeBean.DataBean.FenleiBean> fenleiBeans;

    public FZAdapter(Context context, List<HomeBean.DataBean.FenleiBean> fenleiBeans) {
        this.context = context;
        this.fenleiBeans = fenleiBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(View.inflate(context, R.layout.fz_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.text_zuoFen.setText(fenleiBeans.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemFenLeiClickListener != null){
                    onItemFenLeiClickListener.onItemFenLeiClick(position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        if (fenleiBeans!=null){
            return fenleiBeans.size();
        }else {
            return 0;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView text_zuoFen;
        public ViewHolder(View itemView) {
            super(itemView);
            text_zuoFen = (TextView) itemView.findViewById(R.id.text_zuoFen);
        }
    }

    //自定义一个分类的监听
    private OnItemFenLeiClickListener onItemFenLeiClickListener;
    public interface OnItemFenLeiClickListener{
        void onItemFenLeiClick(int position);
    }

    public void setOnItemFenLeiClickListener(OnItemFenLeiClickListener onItemFenLeiClickListener) {
        this.onItemFenLeiClickListener = onItemFenLeiClickListener;
    }
}
