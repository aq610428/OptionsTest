package com.jkabe.app.box.adapter;

import android.content.Context;
import android.view.View;

import com.jkabe.app.box.bean.AssetsBean;
import com.jkabe.app.box.bean.Travrt;
import com.jkabe.app.box.bean.TripVo;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;

import java.util.List;

/**
 * @author: zt
 * @date: 2020/7/9
 * @name:MinIngAdapter
 */
public class TripAdapter extends AutoRVAdapter {
    private List<TripVo> list;

    public TripAdapter(Context context, List<TripVo> list) {
        super(context, list);
        this.list = list;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_trip;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        TripVo travrt = list.get(position);
        if (position==0){
            vh.getLinearLayout(R.id.ll_tab).setVisibility(View.VISIBLE);
        }else{
            vh.getLinearLayout(R.id.ll_tab).setVisibility(View.GONE);
        }
        vh.getTextView(R.id.text_id).setText(travrt.getBlockid()+"");
        if (!Utility.isEmpty(travrt.getStartTime())){
            String ss=travrt.getStartTime().substring(5,10);
            String ss1=travrt.getStartTime().substring(travrt.getStartTime().length()-8,travrt.getStartTime().length()-3);
            vh.getTextView(R.id.text_ignition).setText(ss+" "+ss1);

        }
        if (!Utility.isEmpty(travrt.getEndTime())){
            String ss=travrt.getEndTime().substring(5,10);
            String ss1=travrt.getEndTime().substring(travrt.getEndTime().length()-8,travrt.getEndTime().length()-3);
            vh.getTextView(R.id.text_fire).setText(ss+" "+ss1);
        }

        if (!Utility.isEmpty(travrt.getUploadTime())){
            String ss=travrt.getUploadTime().substring(5,10);
            String ss1=travrt.getUploadTime().substring(travrt.getUploadTime().length()-8,travrt.getUploadTime().length()-3);
            vh.getTextView(R.id.text_time).setText(ss+" "+ss1);
        }

    }

    public void setData(List<TripVo> travrts) {
        this.list=travrts;
    }
}
