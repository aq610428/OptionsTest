package com.jkabe.app.box.adapter;

import android.content.Context;

import com.jkabe.app.box.bean.OreInfo;
import com.jkabe.box.R;

import java.util.List;

/**
 * @author: zt
 * @date: 2020/9/27
 * @name:AddressAdapter
 */
public class DefiListAdapter extends AutoRVAdapter {
    public List<OreInfo> list;
    public Context context;

    public DefiListAdapter(Context context, List<OreInfo> list) {
        super(context, list);
        this.list = list;
        this.context = context;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_defi_list;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        OreInfo beans = list.get(position);


    }

    public void setData(List<OreInfo> beanList) {
        this.list = beanList;
    }
}
