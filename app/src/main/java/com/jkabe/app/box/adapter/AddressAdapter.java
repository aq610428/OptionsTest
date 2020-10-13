package com.jkabe.app.box.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.jkabe.app.box.bean.AddressBean;
import com.jkabe.app.box.ui.AddressActivity;
import com.jkabe.box.R;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/9/27
 * @name:AddressAdapter
 */
public class AddressAdapter extends AutoRVAdapter {
    List<AddressBean> list;
    Context context;

    public AddressAdapter(Context context, List<AddressBean> list) {
        super(context, list);
        this.list = list;
        this.context = context;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_address_info;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        AddressBean addressBean = list.get(position);
        vh.getTextView(R.id.text_name).setText(addressBean.getReceivename() + "  " + addressBean.getMobile());
        vh.getTextView(R.id.text_address).setText(addressBean.getProvince() + addressBean.getCity() + addressBean.getArea() + addressBean.getAddress());

        if (addressBean.getSetdefault() == 1) {
            vh.getTextView(R.id.text_def).setVisibility(View.VISIBLE);
        } else {
            vh.getTextView(R.id.text_def).setVisibility(View.GONE);
        }
        vh.getImageView(R.id.iv_edio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddressActivity.class);
                intent.putExtra("addressBean", addressBean);
                context.startActivity(intent);
            }
        });
    }

    public void setData(List<AddressBean> beanList) {
        this.list = beanList;
    }
}
