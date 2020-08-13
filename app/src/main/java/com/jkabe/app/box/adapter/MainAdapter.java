package com.jkabe.app.box.adapter;

import android.content.Context;
import android.view.View;

import com.jkabe.app.box.bean.EnceInfo;
import com.jkabe.app.box.bean.TripVo;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;

import java.util.List;

/**
 * @author: zt
 * @date: 2020/7/9
 * @name:MinIngAdapter
 */
public class MainAdapter extends AutoRVAdapter {
    private List<EnceInfo> list;

    public MainAdapter(Context context, List<EnceInfo> list) {
        super(context, list);
        this.list = list;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_main;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        EnceInfo travrt = list.get(position);
        if (position==0){
            vh.getLinearLayout(R.id.ll_tab).setVisibility(View.VISIBLE);
        }else{
            vh.getLinearLayout(R.id.ll_tab).setVisibility(View.GONE);
        }
        vh.getTextView(R.id.text_id).setText(travrt.getBlockid()+"");

        if (!Utility.isEmpty(travrt.getOrderTime())){
            String ss=travrt.getOrderTime().substring(5,10);
            String ss1=travrt.getOrderTime().substring(travrt.getOrderTime().length()-8,travrt.getOrderTime().length()-3);
            vh.getTextView(R.id.text_ignition).setText(ss+" "+ss1);

        }
        if (!Utility.isEmpty(travrt.getFinishTime())){
            String ss=travrt.getFinishTime().substring(5,10);
            String ss1=travrt.getFinishTime().substring(travrt.getFinishTime().length()-8,travrt.getFinishTime().length()-3);
            vh.getTextView(R.id.text_fire).setText(ss+" "+ss1);
        }

        vh.getTextView(R.id.text_time).setText(travrt.getFaultRemark());

    }

    public void setData(List<EnceInfo> travrts) {
        this.list=travrts;
    }
}
