package com.jkabe.app.box.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;

import com.jkabe.app.box.bean.AddressBean;
import com.jkabe.app.box.bean.LocgistBean;
import com.jkabe.app.box.ui.AddressActivity;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;

import java.util.List;

/**
 * @author: zt
 * @date: 2020/9/27
 * @name:AddressAdapter
 */
public class LogisticsAdapter extends AutoRVAdapter {
    List<LocgistBean> list;
    Context context;

    public LogisticsAdapter(Context context, List<LocgistBean> list) {
        super(context, list);
        this.list = list;
        this.context = context;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_address_logistics;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        vh.getTextView(R.id.text_name).setText(list.get(position).getContent());
        String time = list.get(position).getTime();
        if (!Utility.isEmpty(time)) {
            time = time.substring(0, 10) + " " + time.substring(time.length() - 8, time.length());
            vh.getTextView(R.id.text_date).setText(time);
        }
        if (position==0){
            vh.getLinearLayout(R.id.ll_tab1).setVisibility(View.GONE);
            vh.getLinearLayout(R.id.ll_tab2).setVisibility(View.VISIBLE);
        }else{
            vh.getLinearLayout(R.id.ll_tab1).setVisibility(View.VISIBLE);
            vh.getLinearLayout(R.id.ll_tab2).setVisibility(View.GONE);
        }


    }

    public void setData(List<LocgistBean> beanList) {
        this.list = beanList;
    }
}
