package com.jkabe.app.box.adapter;

import android.content.Context;
import android.view.View;

import com.jkabe.app.box.bean.InsureInfo;
import com.jkabe.app.box.bean.Violation;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;

import java.util.List;

/**
 * @author: zt
 * @date: 2020/7/9
 * @name:MinIngAdapter
 */
public class InsurAdapter1 extends AutoRVAdapter {
    private List<Violation> list;

    public InsurAdapter1(Context context, List<Violation> list) {
        super(context, list);
        this.list = list;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_main;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        Violation travrt = list.get(position);
        vh.getTextView(R.id.text_name).setText("违章类型");
        if (position==0){
            vh.getLinearLayout(R.id.ll_tab).setVisibility(View.VISIBLE);
        }else{
            vh.getLinearLayout(R.id.ll_tab).setVisibility(View.GONE);
        }
        vh.getTextView(R.id.text_id).setText(travrt.getBlockid()+"");
        if (!Utility.isEmpty(travrt.getVioTime())){
            String ss=travrt.getVioTime().substring(5,10);
            String ss1=travrt.getVioTime().substring(travrt.getVioTime().length()-8,travrt.getVioTime().length()-3);
            vh.getTextView(R.id.text_ignition).setText(ss+" "+ss1);

        }
        if (!Utility.isEmpty(travrt.getVioHandletime())){
            String ss=travrt.getVioHandletime().substring(5,10);
            String ss1=travrt.getVioHandletime().substring(travrt.getVioHandletime().length()-8,travrt.getVioHandletime().length()-3);
            vh.getTextView(R.id.text_fire).setText(ss+" "+ss1);
        }


        vh.getTextView(R.id.text_time).setText(travrt.getVioCode());

    }

    public void setData(List<Violation> travrts) {
        this.list=travrts;
    }
}
