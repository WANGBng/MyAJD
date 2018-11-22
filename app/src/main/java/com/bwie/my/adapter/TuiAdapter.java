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
import com.bwie.my.bean.HomeBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by wangbingjun on 2018/11/13.
 */

public class TuiAdapter extends RecyclerView.Adapter<TuiAdapter.ViewHolder>{
    private Context context;
    private List<HomeBean.DataBean.TuijianBean.ListBeanX> list;

    public TuiAdapter(Context context, List<HomeBean.DataBean.TuijianBean.ListBeanX> list) {
        this.context = context;
        this.list = list;
    }

   /* public interface OnTuiClickListener {
        void onTuiClickListener(int position);
    }
    private OnTuiClickListener tuiClickListener;

    public void setTuiClickListener(OnTuiClickListener tuiClickListener) {
        this.tuiClickListener = tuiClickListener;
    }
*/
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.tuiitem,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Uri uri = Uri.parse(list.get(position).getImages().split("\\|")[0]);
        holder.tui_simple.setImageURI(uri);
        holder.text_price.setText("￥"+list.get(position).getPrice());
        holder.text_tui.setText(list.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,InfoActivity.class);
                int pid = list.get(position).getPid();
                intent.putExtra("pid",pid);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView text_tui;
        private final TextView text_price;
        private final SimpleDraweeView tui_simple;
        public ViewHolder(View itemView) {
            super(itemView);
            text_tui = itemView.findViewById(R.id.text_tui);
            text_price = itemView.findViewById(R.id.text_price);
            tui_simple = itemView.findViewById(R.id.tui_simple);
        }
    }
}
