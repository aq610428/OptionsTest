package com.jkabe.app.box.adapter;

import android.content.Context;
import com.jkabe.app.box.bean.BoxInfo;
import com.jkabe.box.R;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/7/9
 * @name:MinIngAdapter
 */
public class IncomeAdapter extends AutoRVAdapter {
    private List<BoxInfo> list;

    public IncomeAdapter(Context context, List<BoxInfo> list) {
        super(context, list);
        this.list = list;
    }


    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_income;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        BoxInfo boxInfo = list.get(position);
        vh.getTextView(R.id.text_date).setText(boxInfo.getSumdate());
        vh.getTextView(R.id.text_price).setText("数量BOX:" + boxInfo.getBox() + "");
        vh.getTextView(R.id.text_usd).setText("BOX价格:" + boxInfo.getBoxPrice());
    }

    public void setData(List<BoxInfo> data) {
        this.list = data;
    }
}
