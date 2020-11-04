package com.jkabe.app.box.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jkabe.app.box.bean.AddressBean;
import com.jkabe.app.box.bean.OreInfo;
import com.jkabe.app.box.bean.TabBean;
import com.jkabe.app.box.ui.AddressActivity;
import com.jkabe.box.R;

import java.util.List;

/**
 * @author: zt
 * @date: 2020/9/27
 * @name:AddressAdapter
 */
public class DefiAdapter extends AutoRVAdapter {
    List<TabBean.ItemsBean> list;
    Context context;

    public DefiAdapter(Context context, List<TabBean.ItemsBean> list) {
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
        TabBean.ItemsBean beans = list.get(position);
        vh.getTextView(R.id.text_mouth).setText(beans.getZq());
        vh.getTextView(R.id.text_profit).setText(beans.getLv());
        vh.getTextView(R.id.text_title).setText(beans.getMax()+"-"+beans.getMin()+" BOX 100的整数倍递增");
    }

    public void setData(List<TabBean.ItemsBean> beanList) {
        this.list = beanList;
    }
}
