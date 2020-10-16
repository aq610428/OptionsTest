package com.jkabe.app.box.adapter;

import android.content.Intent;
import android.view.View;

import com.jkabe.app.box.bean.OrderVo;
import com.jkabe.app.box.box.LogisticsActivity;
import com.jkabe.app.box.box.OrderDetileActivity1;
import com.jkabe.app.box.glide.GlideUtils;
import com.jkabe.app.box.weight.DialogUtils;
import com.jkabe.box.R;

import java.util.List;

/**
 * @author: zt
 * @date: 2020/7/9
 * @name:MinIngAdapter
 */
public class OrderListAdapter3 extends AutoRVAdapter {
    private List<OrderVo.GooditemsBean> list;
    private OrderDetileActivity1 activity1;

    public OrderListAdapter3(OrderDetileActivity1 activity1, List<OrderVo.GooditemsBean> list) {
        super(activity1, list);
        this.list = list;
        this.activity1 = activity1;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_order_list1;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        OrderVo.GooditemsBean bean = list.get(position);
        GlideUtils.setImageUrl(bean.getSmallImg(), vh.getImageView(R.id.iv_logo));
        vh.getTextView(R.id.text_name).setText(bean.getTitle());
        vh.getTextView(R.id.text_price).setText("￥" + bean.getSellPrice());
        vh.getTextView(R.id.text_num).setText("x" + bean.getGoodNumber());

        if (bean.getOrderStatus() == 2) {
            vh.getTextView(R.id.text_confirm).setText("催发货");
            vh.getTextView(R.id.text_buy).setVisibility(View.GONE);
            vh.getTextView(R.id.text_confirm).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity1.showUrge(bean.getOrderid());
                }
            });
        } else if (bean.getOrderStatus() == 3) {
            vh.getTextView(R.id.text_buy).setVisibility(View.VISIBLE);
            vh.getTextView(R.id.text_confirm).setText("确认收货");
            vh.getTextView(R.id.text_confirm).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogUtils.showConfirm(activity1, "是否确定收货？", bean.getId());
                }
            });
        } else if (bean.getOrderStatus() == 4 || bean.getOrderStatus() == 8) {
            vh.getTextView(R.id.text_buy).setVisibility(View.VISIBLE);
            vh.getTextView(R.id.text_confirm).setText("已收货");
            vh.getTextView(R.id.text_confirm).setVisibility(View.GONE);
        }

        vh.getTextView(R.id.text_buy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity1, LogisticsActivity.class);
                intent.putExtra("goodBean", bean);
                intent.putExtra("address", activity1.orderBean.getOrderinfo().getReceiveAddress());
                activity1.startActivity(intent);
            }
        });

    }


}
