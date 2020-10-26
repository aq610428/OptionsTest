package com.jkabe.app.box.adapter;

import android.content.Context;

import com.jkabe.app.box.bean.BoxInfo;
import com.jkabe.app.box.util.BigDecimalUtils;
import com.jkabe.app.box.util.TypefaceUtil;
import com.jkabe.box.R;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/7/9
 * @name:MinIngAdapter
 */
public class IncomeAdapter extends AutoRVAdapter {
    private List<BoxInfo> list;
    private Context context;

    public IncomeAdapter(Context context, List<BoxInfo> list) {
        super(context, list);
        this.list = list;
        this.context=context;
    }


    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_income;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        BoxInfo boxInfo = list.get(position);
        vh.getTextView(R.id.text_date).setText(boxInfo.getSumdate());
        vh.getTextView(R.id.text_price).setText(boxInfo.getBox() + "");
        if (boxInfo.getBoxPrice() == 0.0) {
            vh.getTextView(R.id.text_usd).setText("￥--");
        } else {
            vh.getTextView(R.id.text_usd).setText("￥" + BigDecimalUtils.round(BigDecimalUtils.mul(new BigDecimal(boxInfo.getBoxPrice()), new BigDecimal(boxInfo.getBox())), 4));
        }
        TypefaceUtil.setTextType(context, "DINOT-Bold.ttf", vh.getTextView(R.id.text_price));
        TypefaceUtil.setTextType(context, "DINOT-Bold.ttf", vh.getTextView(R.id.text_usd));
    }

    public void setData(List<BoxInfo> data) {
        this.list = data;
    }
}
