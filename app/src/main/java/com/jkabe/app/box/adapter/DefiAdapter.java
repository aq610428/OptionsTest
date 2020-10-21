package com.jkabe.app.box.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jkabe.app.box.bean.AddressBean;
import com.jkabe.app.box.bean.OreInfo;
import com.jkabe.app.box.ui.AddressActivity;
import com.jkabe.box.R;

import java.util.List;

/**
 * @author: zt
 * @date: 2020/9/27
 * @name:AddressAdapter
 */
public class DefiAdapter extends AutoRVAdapter {
    List<OreInfo> list;
    Context context;

    public DefiAdapter(Context context, List<OreInfo> list) {
        super(context, list);
        this.list = list;
        this.context = context;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_defi;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        OreInfo addressBean = list.get(position);


    }

    public void setData(List<OreInfo> beanList) {
        this.list = beanList;
    }
}
