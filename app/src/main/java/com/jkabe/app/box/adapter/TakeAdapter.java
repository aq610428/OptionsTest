package com.jkabe.app.box.adapter;

import android.content.Context;
import android.view.View;

import com.jkabe.app.box.bean.ImageInfo;
import com.jkabe.app.box.glide.GlideUtils;
import com.jkabe.box.R;

import java.util.List;


/**
 * @author: zt
 * @date: 2020/9/22
 * @name:TakeAdapter
 */
public class TakeAdapter extends AutoRVAdapter {
    private List<ImageInfo> list;

    public TakeAdapter(Context context, List<ImageInfo> list) {
        super(context, list);
        this.list = list;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_take;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        GlideUtils.CreateImageRound(list.get(position).getPhotoFile(),vh.getImageView(R.id.iv_logo),5);
        vh.getTextView(R.id.text_logistics).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
