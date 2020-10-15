package com.jkabe.app.box.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jkabe.app.box.bean.OrderBean;
import com.jkabe.app.box.box.OrderDetileActivity;
import com.jkabe.app.box.box.OrderDetileActivity1;
import com.jkabe.app.box.box.fragement.CompletedFragment;
import com.jkabe.app.box.box.fragement.TakeFragment;
import com.jkabe.app.box.util.Utility;
import com.jkabe.app.box.weight.DialogUtils;
import com.jkabe.box.R;

import java.util.List;


/**
 * @author: zt
 * @date: 2020/9/22
 * @name:TakeAdapter
 */
public class TakeAdapter2 extends AutoRVAdapter {
    public List<OrderBean> list;
    private CompletedFragment allFragment;

    public TakeAdapter2(CompletedFragment allFragment, List<OrderBean> list) {
        super(allFragment.getContext(), list);
        this.list = list;
        this.allFragment = allFragment;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_take_vh2;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        OrderBean orderBean = list.get(position);
        if (orderBean.getItems() != null && orderBean.getItems().size() > 0) {
            RecyclerView recyclerView = vh.getRecyclerView(R.id.recyclerView);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(allFragment.getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            OrderListAdapter2 adapter = new OrderListAdapter2(allFragment, orderBean.getItems(),orderBean);
            recyclerView.setAdapter(adapter);
            vh.getTextView(R.id.text_num).setText("共" + orderBean.getItems().size() + "件");
            adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(allFragment.getContext(), OrderDetileActivity1.class);
                    intent.putExtra("id", orderBean.getId());
                    allFragment.getContext().startActivity(intent);
                }
            });
        }
        vh.getTextView(R.id.text_count).setText("实付 ￥" + orderBean.getGoodMoney());

        if (!Utility.isEmpty(orderBean.getOrdertime())) {
            String[] str = orderBean.getOrdertime().split("T");
            vh.getTextView(R.id.text_date).setText(str[0] + " " + str[1].substring(0, str[1].length() - 5));
        }
        vh.getTextView(R.id.text_stats).setText("订单已发货");
    }

    public void setData(List<OrderBean> beanList) {
        this.list = beanList;
    }
}
