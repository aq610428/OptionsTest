package com.jkabe.app.box.adapter;

import android.content.Context;
import android.view.View;

import com.jkabe.app.box.bean.EnceInfo;
import com.jkabe.app.box.bean.InsureInfo;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;

import java.util.List;

/**
 * @author: zt
 * @date: 2020/7/9
 * @name:MinIngAdapter
 */
public class InsurAdapter extends AutoRVAdapter {
    private List<InsureInfo> list;

    public InsurAdapter(Context context, List<InsureInfo> list) {
        super(context, list);
        this.list = list;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_main;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        InsureInfo travrt = list.get(position);
        if (position==0){
            vh.getLinearLayout(R.id.ll_tab).setVisibility(View.VISIBLE);
        }else{
            vh.getLinearLayout(R.id.ll_tab).setVisibility(View.GONE);
        }
        vh.getTextView(R.id.text_id).setText(travrt.getBlockid()+"");
        vh.getTextView(R.id.text_ignition).setText(travrt.getIsuranceStarttime()+"");
        vh.getTextView(R.id.text_fire).setText(travrt.getIsuranceEndtime()+"");

        vh.getTextView(R.id.text_time).setText(travrt.getIsuranceName());

    }

    public void setData(List<InsureInfo> travrts) {
        this.list=travrts;
    }
}
