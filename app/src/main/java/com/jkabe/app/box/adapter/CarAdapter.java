package com.jkabe.app.box.adapter;

import android.content.Context;

import com.jkabe.app.box.bean.OrderInfo;
import com.jkabe.app.box.bean.OreInfo;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;
import com.jkabe.app.box.bean.Money;
import com.jkabe.app.box.glide.GlideUtils;
import com.jkabe.app.box.util.BigDecimalUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/26
 * @name:清单
 */
public class CarAdapter extends AutoRVAdapter {
    private List<OreInfo> inventories = new ArrayList<>();
    private Context mContext;

    public CarAdapter(Context context, List<OreInfo> inventories) {
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
        return R.layout.item_keep_car;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        OreInfo inventory = inventories.get(position);
        vh.getTextView(R.id.text_name).setText(inventory.getName() + "");

        if (!Utility.isEmpty(inventory.getQuotation().getOpen())) {
            BigDecimal price = BigDecimalUtils.subLastBit(Double.parseDouble(inventory.getQuotation().getClose()), 4);
            vh.getTextView(R.id.text_price).setText("$:" + price.toPlainString());
        }
        BigDecimal price = BigDecimalUtils.mul(new BigDecimal(inventory.getQuotation().getClose()), new BigDecimal(inventory.getCny_price()));
        vh.getTextView(R.id.text_price_cny).setText("￥:" + BigDecimalUtils.round(price, 4));
        BigDecimal vol=BigDecimalUtils.div(new BigDecimal(inventory.getQuotation().getVol()), new BigDecimal(1000),4);
        vh.getTextView(R.id.text_traveled).setText("24H 量 " + vol.toPlainString()+"K");

        BigDecimal scale = BigDecimalUtils.sub(new BigDecimal(inventory.getQuotation().getOpen()), new BigDecimal(inventory.getQuotation().getClose()));
        BigDecimal div=BigDecimalUtils.div(scale,new BigDecimal(inventory.getQuotation().getOpen()),2);
        double str=BigDecimalUtils.mul(div,new BigDecimal(100)).doubleValue();


        if (scale.doubleValue()>0) {
            vh.getTextView(R.id.text_Increase).setBackgroundResource(R.drawable.shape_login);
            vh.getTextView(R.id.text_Increase).setText("-"+Math.abs(str) + "%");
        } else {
            vh.getTextView(R.id.text_Increase).setText(Math.abs(str) + "%");
            vh.getTextView(R.id.text_Increase).setBackgroundResource(R.drawable.shape_login_red);
        }




        GlideUtils.setImageUrl(inventory.getImg(), vh.getImageView(R.id.iv_logo));
    }


}
