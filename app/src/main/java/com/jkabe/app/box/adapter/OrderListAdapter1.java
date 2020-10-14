package com.jkabe.app.box.adapter;

import android.content.Context;

import com.jkabe.app.box.bean.OrderBean;
import com.jkabe.app.box.bean.OrderVo;
import com.jkabe.app.box.glide.GlideUtils;
import com.jkabe.box.R;

import java.util.List;

/**
 * @author: zt
 * @date: 2020/7/9
 * @name:MinIngAdapter
 */
public class OrderListAdapter1 extends AutoRVAdapter {
    private List<OrderVo.GooditemsBean> list;

    public OrderListAdapter1(Context context, List<OrderVo.GooditemsBean> list) {
        super(context, list);
        this.list = list;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_order_list;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        OrderVo.GooditemsBean bean=list.get(position);
        GlideUtils.setImageUrl(bean.getSmallImg(),vh.getImageView(R.id.iv_logo));
        vh.getTextView(R.id.text_name).setText(bean.getTitle());
        vh.getTextView(R.id.text_price).setText("￥"+bean.getSellPrice());
        vh.getTextView(R.id.text_num).setText("x"+bean.getGoodNumber());
    }


}
