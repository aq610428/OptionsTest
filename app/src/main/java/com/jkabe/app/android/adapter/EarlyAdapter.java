package com.jkabe.app.android.adapter;

import com.jkabe.box.R;
import com.jkabe.app.android.bean.EarlyInfo;
import com.jkabe.app.android.bean.Electronic;
import com.jkabe.app.android.ui.EarlyActivity;
import com.jkabe.app.android.ui.ElectronicActivity;
import com.jkabe.app.android.util.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/26
 * @name:清单
 */
public class EarlyAdapter extends AutoRVAdapter {
    private List<EarlyInfo> inventories = new ArrayList<>();
    private EarlyActivity activity;

    public EarlyAdapter(EarlyActivity activity, List<EarlyInfo> inventories) {
        super(activity, inventories);
        this.activity = activity;
        this.inventories = inventories;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_early;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        EarlyInfo electronic = inventories.get(position);
        vh.getTextView(R.id.text_name).setText(electronic.getTitle());
        vh.getTextView(R.id.text_name).setText(electronic.getTitle());
        vh.getTextView(R.id.text_brand).setText(electronic.getContent());
        String ss = electronic.getStringgpstime();
        if (!Utility.isEmpty(ss)) {
            String start = ss.substring(0, ss.length() - 8);
            String end = ss.substring(ss.length() - 8, ss.length());
            vh.getTextView(R.id.text_edition).setText(start + " " + end);
        }

    }


    public void setData(List<EarlyInfo> electronics) {
        this.inventories = electronics;
    }
}
