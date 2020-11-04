package com.jkabe.app.box.adapter;

import android.view.View;

import com.jkabe.app.box.bean.BoxVo;
import com.jkabe.app.box.box.TabActivity;
import com.jkabe.app.box.box.TabDefiActivity;
import com.jkabe.app.box.util.BigDecimalUtils;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/9/27
 * @name:AddressAdapter
 */
public class TabAdapter1 extends AutoRVAdapter {
    List<BoxVo> list;
    TabDefiActivity activity;

    public TabAdapter1(TabDefiActivity activity, List<BoxVo> list) {
        super(activity, list);
        this.list = list;
        this.activity = activity;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_tab1;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        BoxVo boxVo = list.get(position);
        vh.getTextView(R.id.text_stats).setText(boxVo.getAmount() + "BOX");

        if (!Utility.isEmpty(boxVo.getUpdateTime())) {
            String[] str = boxVo.getUpdateTime().split("T");
            if (str != null && str.length > 1) {
                vh.getTextView(R.id.text_start).setText(str[0] + " " + str[1].substring(0, 8));
            }
        }
        BigDecimal decimal = BigDecimalUtils.mul(new BigDecimal(boxVo.getLv()), new BigDecimal(100));
        vh.getTextView(R.id.text_type1).setText(BigDecimalUtils.round(decimal, 2).toPlainString() + "%");
        vh.getTextView(R.id.text_orderId).setText(boxVo.getOrderid() + "");
    }

    public void setData(List<BoxVo> beans) {
        this.list = beans;
    }
}
