package com.jkabe.app.box.adapter;

import android.content.Context;
import com.jkabe.app.box.bean.EarlyInfo;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/7/9
 * @name:MinIngAdapter
 */
public class AdvertAdapter extends AutoRVAdapter {
    private List<EarlyInfo> list;

    public AdvertAdapter(Context context, List<EarlyInfo> list) {
        super(context, list);
        this.list = list;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_advert;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        EarlyInfo earlyInfo = list.get(position);
        vh.getTextView(R.id.text_msg).setText(earlyInfo.getContent());
        String ss = earlyInfo.getStringgpstime();
        if (!Utility.isEmpty(ss)) {
            String start = ss.substring(0, ss.length() - 8);
            String end = ss.substring(ss.length() - 8, ss.length());
            vh.getTextView(R.id.text_date).setText(start + " " + end);
        }
    }

    public void setData(List<EarlyInfo> data) {
        this.list = data;
    }
}
