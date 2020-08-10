package com.jkabe.app.box.adapter;

import android.content.Context;

import com.jkabe.app.box.bean.Block;
import com.jkabe.box.R;

import java.util.List;

/**
 * @author: zt
 * @date: 2020/8/10
 * @name:BlockAdapter
 */
public class BlockAdapter extends AutoRVAdapter {
    List<Block> blocks;
    public BlockAdapter(Context context,  List<Block> blocks) {
        super(context, blocks);
        this.blocks=blocks;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_block;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        vh.getTextView(R.id.text_name).setText(blocks.get(position).getName());
        vh.getTextView(R.id.text_name).setCompoundDrawablesWithIntrinsicBounds(0,blocks.get(position).getDrawable(),0,0);
    }
}
