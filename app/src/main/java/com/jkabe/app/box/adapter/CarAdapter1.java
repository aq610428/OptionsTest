package com.jkabe.app.box.adapter;

import android.content.Context;
import android.view.View;

import com.jkabe.app.box.bean.Money;
import com.jkabe.app.box.bean.OreInfo;
import com.jkabe.app.box.util.BigDecimalUtils;
import com.jkabe.app.box.util.TypefaceUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/26
 * @name:清单
 */
public class CarAdapter1 extends AutoRVAdapter {
    private List<Money> inventories = new ArrayList<>();
    private Context mContext;

    public CarAdapter1(Context context, List<Money> inventories) {
        super(context, inventories);
        this.mContext = context;
        this.inventories = inventories;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_keep_car1;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        Money inventory = inventories.get(position);
        vh.getTextView(R.id.text_name).setText(inventory.getSymbol().toUpperCase());
        if (!Utility.isEmpty(inventory.getPrice_usd())) {
            BigDecimal price = BigDecimalUtils.subLastBit(inventory.getPrice_usd(), 4);
            vh.getTextView(R.id.text_price).setText("$:" + price.toPlainString());
        }
        BigDecimal price = BigDecimalUtils.subLastBit(inventory.getPrice_cny(), 4);
        vh.getTextView(R.id.text_price_cny).setText("≈￥:" + BigDecimalUtils.round(price, 4));
        vh.getTextView(R.id.text_traveled).setText("24H 量 " + BigDecimalUtils.round(new BigDecimal(inventory.getVolume_24h_usd() ),2).toPlainString()+ "K");


        TypefaceUtil.setTextType(mContext, "DINOT-Bold.ttf", vh.getTextView(R.id.text_price));
        TypefaceUtil.setTextType(mContext, "DINOT-Bold.ttf", vh.getTextView(R.id.text_name));
        TypefaceUtil.setTextType(mContext, "DINOT-Bold.ttf", vh.getTextView(R.id.text_Increase));
        BigDecimal str = BigDecimalUtils.round(new BigDecimal(inventory.getPercent_change_24h()), 2);
        if (inventory.getPercent_change_24h() < 0) {
            vh.getTextView(R.id.text_Increase).setBackgroundResource(R.drawable.shape_login_red);
            vh.getTextView(R.id.text_Increase).setText(str+"%");
        } else {
            vh.getTextView(R.id.text_Increase).setText(str+ "%");
            vh.getTextView(R.id.text_Increase).setBackgroundResource(R.drawable.shape_login);
        }
    }
}
