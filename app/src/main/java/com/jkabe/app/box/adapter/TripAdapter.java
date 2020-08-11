package com.jkabe.app.box.adapter;

import android.content.Context;
import android.view.View;

import com.jkabe.app.box.bean.AssetsBean;
import com.jkabe.app.box.bean.Travrt;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;

import java.util.List;

/**
 * @author: zt
 * @date: 2020/7/9
 * @name:MinIngAdapter
 */
public class TripAdapter extends AutoRVAdapter {
    private List<Travrt> list;

    public TripAdapter(Context context, List<Travrt> list) {
        super(context, list);
        this.list = list;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_trip;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        Travrt travrt = list.get(position);
        if (position==0){
            vh.getLinearLayout(R.id.ll_tab).setVisibility(View.VISIBLE);
        }else{
            vh.getLinearLayout(R.id.ll_tab).setVisibility(View.GONE);
        }
        vh.getTextView(R.id.text_id).setText(travrt.getTripmileage()+"");
        if (!Utility.isEmpty(travrt.getStarttime())){
            String ss=travrt.getStarttime().substring(5,10);
            String ss1=travrt.getStarttime().substring(travrt.getStarttime().length()-8,travrt.getStarttime().length()-3);
            vh.getTextView(R.id.text_ignition).setText(ss+" "+ss1);
            vh.getTextView(R.id.text_time).setText(ss+" "+ss1);
        }

        if (!Utility.isEmpty(travrt.getEndtime())){
            String ss=travrt.getEndtime().substring(5,10);
            String ss1=travrt.getEndtime().substring(travrt.getEndtime().length()-8,travrt.getEndtime().length()-3);
            vh.getTextView(R.id.text_fire).setText(ss+" "+ss1);
        }
    }

}
