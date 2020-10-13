package com.jkabe.app.box.adapter;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jkabe.app.box.bean.OrderBean;
import com.jkabe.app.box.box.fragement.AllFragment;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;

import java.util.List;


/**
 * @author: zt
 * @date: 2020/9/22
 * @name:TakeAdapter
 */
public class TakeAdapter extends AutoRVAdapter {
    private List<OrderBean> list;
    private AllFragment allFragment;

    public TakeAdapter(AllFragment allFragment, List<OrderBean> list) {
        super(allFragment.getContext(), list);
        this.list = list;
        this.allFragment = allFragment;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_take;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        OrderBean orderBean = list.get(position);
        if (orderBean.getItems() != null && orderBean.getItems().size() > 0) {
            RecyclerView recyclerView = vh.getRecyclerView(R.id.recyclerView);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(allFragment.getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            OrderListAdapter adapter = new OrderListAdapter(allFragment.getContext(), orderBean.getItems());
            recyclerView.setAdapter(adapter);
            vh.getTextView(R.id.text_num).setText("共" + orderBean.getItems().size() + "件");
        }
        vh.getTextView(R.id.text_count).setText("订单总额 ￥" + orderBean.getGoodMoney());

        if (!Utility.isEmpty(orderBean.getOrdertime())) {
            String[] str = orderBean.getOrdertime().split("T");
            vh.getTextView(R.id.text_date).setText(str[0] + " " + str[1].substring(0, str[1].length() - 5));
        }


        switch (orderBean.getOrderStatus()) {
            case 1:
                vh.getTextView(R.id.text_stats).setText("待支付");
                vh.getRelativeLayout(R.id.rl_pay).setVisibility(View.VISIBLE);

                break;
            case 2:
                vh.getTextView(R.id.text_stats).setText("待发货");
                vh.getRelativeLayout(R.id.rl_pay).setVisibility(View.GONE);
                break;
            case 3:
                vh.getTextView(R.id.text_stats).setText("已发货");
                vh.getRelativeLayout(R.id.rl_pay).setVisibility(View.GONE);
                break;
            case 4:
                vh.getTextView(R.id.text_stats).setText("已收货");
                vh.getRelativeLayout(R.id.rl_pay).setVisibility(View.GONE);
                break;
            case 5:
                vh.getTextView(R.id.text_stats).setText("订单已取消");
                vh.getRelativeLayout(R.id.rl_pay).setVisibility(View.GONE);
                break;
            case 8:
                vh.getTextView(R.id.text_stats).setText("订单已完成");
                vh.getRelativeLayout(R.id.rl_pay).setVisibility(View.GONE);
                break;
        }

        vh.getTextView(R.id.text_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allFragment.cancelOrder(orderBean.getId());
            }
        });


        vh.getTextView(R.id.text_buy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 allFragment.showTip(orderBean);
            }
        });


    }

    public void setData(List<OrderBean> beanList) {
        this.list = beanList;

    }
}
