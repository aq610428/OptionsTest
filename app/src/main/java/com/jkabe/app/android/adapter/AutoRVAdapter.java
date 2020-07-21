package com.jkabe.app.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

/**
 * 适配器
 */
public abstract class AutoRVAdapter extends RecyclerView.Adapter<RVHolder> {
    public List<?> list;
    private Context context;
    Map<?, ?> map;

    public AutoRVAdapter(Context context, List<?> list) {
        this.list = list;
        this.context = context;
    }


    @Override
    public RVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(onCreateViewLayoutID(viewType), parent, false);
        return new RVHolder(view);
    }

    public abstract int onCreateViewLayoutID(int viewType);


    @Override
    public void onViewRecycled(final RVHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public void onBindViewHolder(final RVHolder holder, final int position) {
        onBindViewHolder(holder.getViewHolder(), position);
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(null, v, holder.getPosition(), holder.getItemId());
                }
            });
        }

    }

    public abstract void onBindViewHolder(ViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return list.size();
    }

    private AdapterView.OnItemClickListener onItemClickListener;

    public AdapterView.OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
