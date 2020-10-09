package com.jkabe.app.box.adapter;


import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.jkabe.app.box.bean.ImageInfo;
import com.jkabe.app.box.glide.GlideUtils;
import com.jkabe.app.box.util.BigDecimalUtils;
import com.jkabe.box.R;

import java.math.BigDecimal;
import java.util.List;


/**
 * @author: zt
 * @date: 2020/9/18
 * @name:ConfimAdapter
 */
public class ConfimAdapter extends AutoRVAdapter {
    List<ImageInfo> list;

    public ConfimAdapter(Context context, List<ImageInfo> list) {
        super(context, list);
        this.list=list;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_config;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        GlideUtils.CreateImageRound(list.get(position).getPhotoFile(),vh.getImageView(R.id.iv_logo),5);
    }
}
