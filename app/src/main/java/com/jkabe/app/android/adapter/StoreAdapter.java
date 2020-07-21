package com.jkabe.app.android.adapter;

import android.content.Context;

import com.jkabe.box.R;
import com.jkabe.app.android.bean.StoreInfo;
import com.jkabe.app.android.glide.GlideUtils;

import java.util.List;


/**
 * @author: zt
 * @date: 2020/5/28
 * @name:StoreAdapter
 */
public class StoreAdapter extends AutoRVAdapter {
    private List<StoreInfo> infos;

    public StoreAdapter(Context context, List<StoreInfo> list) {
        super(context, list);
        this.infos = list;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_store;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        StoreInfo info = infos.get(position);
        vh.getTextView(R.id.text_name).setText(infos.get(position).getName());
        GlideUtils.CreateImageRound(infos.get(position).getLogo(), vh.getImageView(R.id.iv_photo), 5);
        vh.getTextView(R.id.tv_address).setText(info.getAddress());
        vh.getTextView(R.id.text_verify).setText(info.getPhone());

    }

    public void setData(List<StoreInfo> infos) {
        this.infos = infos;
    }
}
