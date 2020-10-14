package com.jkabe.app.box.adapter;

import android.view.View;
import com.jkabe.app.box.bean.OrderBean;
import com.jkabe.app.box.box.fragement.CompletedFragment;
import com.jkabe.app.box.glide.GlideUtils;
import com.jkabe.app.box.weight.DialogUtils;
import com.jkabe.box.R;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/7/9
 * @name:MinIngAdapter
 */
public class OrderListAdapter2 extends AutoRVAdapter {
    private List<OrderBean.ItemsBean> list;
    private CompletedFragment allFragment;

    public OrderListAdapter2(CompletedFragment allFragment, List<OrderBean.ItemsBean> list) {
        super(allFragment.getContext(), list);
        this.list = list;
        this.allFragment=allFragment;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_order_list1;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        OrderBean.ItemsBean bean=list.get(position);
        GlideUtils.setImageUrl(bean.getSmallImg(),vh.getImageView(R.id.iv_logo));
        vh.getTextView(R.id.text_name).setText(bean.getTitle());
        vh.getTextView(R.id.text_price).setText("￥"+bean.getSellPrice());
        vh.getTextView(R.id.text_num).setText("x"+bean.getGoodNumber());
        vh.getTextView(R.id.text_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.showConfirm(allFragment, "是否确定收货？",bean.getId());
            }
        });

    }


}
