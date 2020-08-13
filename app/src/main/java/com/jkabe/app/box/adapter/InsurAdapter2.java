package com.jkabe.app.box.adapter;

import android.content.Context;
import android.view.View;

import com.jkabe.app.box.bean.OilInfo;
import com.jkabe.app.box.bean.Violation;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;

import java.util.List;

/**
 * @author: zt
 * @date: 2020/7/9
 * @name:MinIngAdapter
 */
public class InsurAdapter2 extends AutoRVAdapter {
    private List<OilInfo> list;

    public InsurAdapter2(Context context, List<OilInfo> list) {
        super(context, list);
        this.list = list;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_main;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        OilInfo travrt = list.get(position);
        vh.getTextView(R.id.text_name).setText("加油号");
        vh.getTextView(R.id.text_tab1).setText("上传车牌");
        vh.getTextView(R.id.text_tab2).setText("上传时间");

        if (position==0){
            vh.getLinearLayout(R.id.ll_tab).setVisibility(View.VISIBLE);
        }else{
            vh.getLinearLayout(R.id.ll_tab).setVisibility(View.GONE);
        }
        vh.getTextView(R.id.text_id).setText(travrt.getBlockid()+"");
        vh.getTextView(R.id.text_ignition).setText(travrt.getCarcard());
        if (!Utility.isEmpty(travrt.getOilTime())){
            String ss=travrt.getOilTime().substring(0,10);
            String ss1=travrt.getOilTime().substring(travrt.getOilTime().length()-8,travrt.getOilTime().length()-3);
            vh.getTextView(R.id.text_fire).setText(ss+" "+ss1);
        }


        vh.getTextView(R.id.text_time).setText(travrt.getOilModel());

    }

    public void setData(List<OilInfo> travrts) {
        this.list=travrts;
    }
}
