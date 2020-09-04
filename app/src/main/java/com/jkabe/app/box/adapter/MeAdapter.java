package com.jkabe.app.box.adapter;

import android.content.Context;

import com.jkabe.app.box.bean.Block;
import com.jkabe.box.R;

import java.util.List;

/**
 * @author: zt
 * @date: 2020/9/4
 * @name:MeAdapter
 */
public class MeAdapter extends AutoRVAdapter {
    List<Block> list;

    public MeAdapter(Context context, List<Block> list) {
        super(context, list);
        this.list = list;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_fragment_me;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        vh.getTextView(R.id.text_password).setText(list.get(position).getName());
        vh.getTextView(R.id.text_password).setCompoundDrawablesWithIntrinsicBounds(0, list.get(position).getDrawable(), 0, 0);
    }
}
