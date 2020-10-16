package com.jkabe.app.box.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jkabe.app.box.bean.OrderBean;
import com.jkabe.app.box.box.OrderDetileActivity;
import com.jkabe.app.box.box.OrderDetileActivity1;
import com.jkabe.app.box.box.fragement.AllFragment;
import com.jkabe.app.box.ui.PreviewActivity;
import com.jkabe.app.box.util.Utility;
import com.jkabe.app.box.weight.DialogUtils;
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
            adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int stats = orderBean.getOrderStatus();
                    Intent intent = null;
                    if (stats == 3 || stats == 4 || stats == 8) {
                        intent = new Intent(allFragment.getContext(), OrderDetileActivity1.class);
                    } else {
                        intent = new Intent(allFragment.getContext(), OrderDetileActivity.class);
                    }
                    intent.putExtra("orderStatus", stats + "");
                    intent.putExtra("id", orderBean.getId());
                    allFragment.getContext().startActivity(intent);
                }
            });
        }
        vh.getTextView(R.id.text_count).setText("实付 ￥" + orderBean.getGoodMoney());
        if (!Utility.isEmpty(orderBean.getStringOrdertime())) {
            String stringOrdertime = orderBean.getStringOrdertime();
            vh.getTextView(R.id.text_date).setText(stringOrdertime.substring(0, 10) + " " + stringOrdertime.substring(stringOrdertime.length() - 8, stringOrdertime.length()));
        }

        switch (orderBean.getOrderStatus()) {
            case 1://未支付
                vh.getTextView(R.id.text_stats).setText("待支付");
                vh.getTextView(R.id.text_skills).setVisibility(View.GONE);
                vh.getTextView(R.id.text_Urge).setVisibility(View.GONE);
                vh.getLinearLayout(R.id.rl_tab1).setVisibility(View.VISIBLE);
                vh.getTextView(R.id.text_server).setVisibility(View.GONE);
                vh.getTextView(R.id.text_stats).setText("未支付");
                break;
            case 2://已支付待发货
                vh.getTextView(R.id.text_stats).setText("待发货");
                vh.getLinearLayout(R.id.rl_tab1).setVisibility(View.GONE);
                vh.getTextView(R.id.text_skills).setVisibility(View.GONE);
                vh.getTextView(R.id.text_Urge).setVisibility(View.VISIBLE);
                vh.getTextView(R.id.text_server).setVisibility(View.VISIBLE);
                vh.getTextView(R.id.text_stats).setText("待发货");
                break;
            case 3://已发货
                vh.getTextView(R.id.text_stats).setText("已发货");
                vh.getLinearLayout(R.id.rl_tab1).setVisibility(View.GONE);
                vh.getTextView(R.id.text_Urge).setVisibility(View.GONE);
                vh.getTextView(R.id.text_skills).setVisibility(View.VISIBLE);
                vh.getTextView(R.id.text_server).setVisibility(View.VISIBLE);
                vh.getTextView(R.id.text_stats).setText("待收货");
                break;
            case 4://已确认收货
                vh.getLinearLayout(R.id.rl_tab1).setVisibility(View.GONE);
                vh.getTextView(R.id.text_Urge).setVisibility(View.GONE);
                vh.getTextView(R.id.text_skills).setVisibility(View.VISIBLE);
                vh.getTextView(R.id.text_server).setVisibility(View.VISIBLE);
                vh.getTextView(R.id.text_stats).setText("已收货");
                break;
            case 5://订单取消
            case 8://订单已完成
                vh.getTextView(R.id.text_skills).setVisibility(View.GONE);
                vh.getLinearLayout(R.id.rl_tab1).setVisibility(View.GONE);
                vh.getTextView(R.id.text_Urge).setVisibility(View.GONE);
                vh.getTextView(R.id.text_server).setVisibility(View.VISIBLE);
                break;
        }

        if (orderBean.getOrderStatus() == 5) {
            vh.getTextView(R.id.text_stats).setText("已取消");
        } else if (orderBean.getOrderStatus() == 8) {
            vh.getTextView(R.id.text_stats).setText("已完成");
        }
        vh.getTextView(R.id.text_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.showOrder(allFragment, "是否取消订单？", orderBean.getId());

            }
        });
        vh.getTextView(R.id.text_buy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allFragment.showTip(orderBean);
            }
        });

        vh.getTextView(R.id.text_server).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(allFragment.getContext(), PreviewActivity.class);
                intent.putExtra("name", "加入社群");
                intent.putExtra("url", "http://openapi.jkabe.com/golo/about");
                allFragment.getContext().startActivity(intent);
            }
        });
    }

    public void setData(List<OrderBean> beanList) {
        this.list = beanList;
    }
}
