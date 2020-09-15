package com.jkabe.app.box.adapter;

import android.content.Context;
import android.graphics.Color;

import com.jkabe.app.box.bean.Massage;
import com.jkabe.app.box.bean.PackageBean;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;

import java.util.List;

/**
 * @author: zt
 * @date: 2020/7/9
 * @name:MinIngAdapter
 */
public class OpemAdapter extends AutoRVAdapter {
    private List<PackageBean> list;

    public OpemAdapter(Context context, List<PackageBean> list) {
        super(context, list);
        this.list = list;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_opem;
    }

    private int mPosition = -1;

    public int getmPosition() {
        return mPosition;
    }

    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }


    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        PackageBean massage = list.get(position);
        vh.getTextView(R.id.text_name).setText("套餐名称：" + massage.getPackage_name());
        if (massage.getPackage_period() == 365) {
            vh.getTextView(R.id.text_day).setText("有效时间:  延长一年");
        } else {
            vh.getTextView(R.id.text_day).setText("有效时间:  当月有效");
        }

        if (position == getmPosition()) {
            vh.getLinearLayout(R.id.ll_view).setBackgroundColor(Color.parseColor("#81c2f4"));
        } else {
            vh.getLinearLayout(R.id.ll_view).setBackgroundColor(Color.WHITE);
        }
    }


}
