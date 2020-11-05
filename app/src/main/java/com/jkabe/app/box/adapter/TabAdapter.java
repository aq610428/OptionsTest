package com.jkabe.app.box.adapter;

import android.content.Context;
import android.view.View;

import com.jkabe.app.box.bean.ActiveBean;
import com.jkabe.app.box.bean.BoxVo;
import com.jkabe.app.box.box.TabActivity;
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
public class TabAdapter extends AutoRVAdapter {
    List<BoxVo> list;
    TabActivity activity;

    public TabAdapter(TabActivity activity, List<BoxVo> list) {
        super(activity, list);
        this.list = list;
        this.activity = activity;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_tab;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        BoxVo boxVo = list.get(position);
        switch (boxVo.getType()) {
            case "1":
                vh.getTextView(R.id.text_type1).setText("90天");
                break;
            case "2":
                vh.getTextView(R.id.text_type1).setText("180天");
                break;
            case "3":
                vh.getTextView(R.id.text_type1).setText("一年");
                break;
            case "4":
                vh.getTextView(R.id.text_type1).setText("两年");
                break;
            case "5":
                vh.getTextView(R.id.text_type1).setText("三年");
                break;
        }

        switch (boxVo.getState()) {
            case "1":
                vh.getTextView(R.id.text_stats).setText("未到期");
                vh.getLinearLayout(R.id.ll_redeem).setVisibility(View.VISIBLE);
                break;
            case "2":
                vh.getTextView(R.id.text_stats).setText("已到期");
                vh.getLinearLayout(R.id.ll_redeem).setVisibility(View.GONE);
                break;
            case "3":
                vh.getTextView(R.id.text_stats).setText("已违约");
                vh.getLinearLayout(R.id.ll_redeem).setVisibility(View.GONE);
                break;
        }
        vh.getTextView(R.id.text_box).setText(boxVo.getAmount() + "BOX");
        vh.getTextView(R.id.text_orderId).setText(boxVo.getOrderid()+"");

        vh.getTextView(R.id.text_start).setText(boxVo.getStringOrdertime());
        vh.getTextView(R.id.text_end).setText(boxVo.getStringEndtime());
        BigDecimal decimal = BigDecimalUtils.mul(new BigDecimal(boxVo.getLv()), new BigDecimal(100));
        vh.getTextView(R.id.text_interest).setText(BigDecimalUtils.round(decimal, 2).toPlainString() + "%");
        vh.getTextView(R.id.text_monty).setText(boxVo.getProfitAmount() + "BOX");


        vh.getLinearLayout(R.id.ll_redeem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.showOrder(boxVo.getId(), BigDecimalUtils.round(new BigDecimal(boxVo.getDamagesAmount()),2).toPlainString());
            }
        });
    }

    public void setData(List<BoxVo> beans) {
        this.list = beans;
    }
}
