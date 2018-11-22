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
import com.bwie.my.activity.ShowActivity;
import com.bwie.my.bean.FenBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by wangbingjun on 2018/11/14.
 */

public class ZAdapter extends RecyclerView.Adapter<ZAdapter.ViewHolder> {
    private Context context;
    private List<FenBean.DataBean> fbd;

    public ZAdapter(Context context, List<FenBean.DataBean> fbd) {
        this.context = context;
        this.fbd = fbd;
    }

    private OnItemZClickListener onItemZClickListener;

    public interface OnItemZClickListener{
        void OnItemZClick(int position);
    }

    public void setOnItemZClickListener(OnItemZClickListener onItemZClickListener) {
        this.onItemZClickListener = onItemZClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.z_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.te_you_title.setText(fbd.get(position).getName());
        Uri uri =Uri.parse(fbd.get(position).getList().get(position).getIcon().split("\\|")[0]);

        holder.zSimple.setImageURI(uri);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,ShowActivity.class);
                intent.putExtra("sousuo",fbd.get(position).getList().get(position).getName());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return fbd.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView te_you_title;
        SimpleDraweeView zSimple;
        public ViewHolder(View itemView) {
            super(itemView);
            te_you_title = itemView.findViewById(R.id.te_you_title);
            zSimple = itemView.findViewById(R.id.zSimple);
        }
    }
}
