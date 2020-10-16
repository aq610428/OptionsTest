package com.jkabe.app.box.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jkabe.app.box.bean.OrderBean;
import com.jkabe.app.box.box.OrderDetileActivity;
import com.jkabe.app.box.box.fragement.AllFragment;
import com.jkabe.app.box.box.fragement.PayFragment;
import com.jkabe.app.box.util.Utility;
import com.jkabe.app.box.weight.DialogUtils;
import com.jkabe.box.R;

import java.util.List;


/**
 * @author: zt
 * @date: 2020/9/22
 * @name:TakeAdapter
 */
public class PayAdapter extends AutoRVAdapter {
    private List<OrderBean> list;
    private PayFragment allFragment;

    public PayAdapter(PayFragment allFragment, List<OrderBean> list) {
        super(allFragment.getContext(), list);
        this.list = list;
        this.allFragment = allFragment;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_pay_1;
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
            adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(allFragment.getContext(), OrderDetileActivity.class);
                    intent.putExtra("id", orderBean.getId());
                    intent.putExtra("orderStatus", orderBean.getOrderStatus()+"");
                    allFragment.getContext().startActivity(intent);
                }
            });
        }
        vh.getTextView(R.id.text_count).setText("实付 ￥" + orderBean.getGoodMoney());

        if (!Utility.isEmpty(orderBean.getStringOrdertime())) {
            String stringOrdertime = orderBean.getStringOrdertime();
            vh.getTextView(R.id.text_date).setText(stringOrdertime.substring(0,10) + " " + stringOrdertime.substring(stringOrdertime.length()-8, stringOrdertime.length()));
        }
        vh.getTextView(R.id.text_stats).setText("待支付");
        vh.getTextView(R.id.text_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.showOrder(allFragment,"是否取消订单？",orderBean.getId());
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
