package com.jkabe.app.box.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jkabe.app.box.bean.ActiveBean;
import com.jkabe.app.box.bean.AddressBean;
import com.jkabe.app.box.ui.AddressActivity;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;

import java.util.List;

/**
 * @author: zt
 * @date: 2020/9/27
 * @name:AddressAdapter
 */
public class ActiiveAdapter extends AutoRVAdapter {
    List<ActiveBean> list;
    Context context;

    public ActiiveAdapter(Context context, List<ActiveBean> list) {
        super(context, list);
        this.list = list;
        this.context = context;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_active;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        ActiveBean addressBean = list.get(position);
        vh.getTextView(R.id.text_title).setText(addressBean.getKey());
        if (Utility.isEmpty(addressBean.getMiningChance())) {
            vh.getTextView(R.id.text_userableNum).setText("可用推荐激活挖矿数：0次");
            vh.getTextView(R.id.text_usedNum).setText("已用推荐激活挖矿数：0次");
            vh.getTextView(R.id.text_directCount).setText("直推激活挖矿人数：0人");
        } else {
            if (addressBean.getMiningChance()!=null){
                vh.getTextView(R.id.text_userableNum).setText("可用推荐激活挖矿数：" + addressBean.getMiningChance().getUserableNum() + "次");
                vh.getTextView(R.id.text_usedNum).setText("已用推荐激活挖矿数：" + addressBean.getMiningChance().getUsedNum() + "次");
                vh.getTextView(R.id.text_directCount).setText("直推激活挖矿人数：" + addressBean.getMiningChance().getDirectCoun() + "人");
            }else{
                vh.getTextView(R.id.text_userableNum).setText("可用推荐激活挖矿数：0次");
                vh.getTextView(R.id.text_usedNum).setText("已用推荐激活挖矿数：0次");
                vh.getTextView(R.id.text_directCount).setText("直推激活挖矿人数：0人");
            }

        }

    }

}
