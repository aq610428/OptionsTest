package com.jkabe.app.box.adapter;

import android.content.Intent;
import android.view.View;

import com.jkabe.app.box.bean.AssetsBean;
import com.jkabe.app.box.box.AssetsActivity;
import com.jkabe.app.box.ui.fragment.AssetsFragmnt;
import com.jkabe.box.R;

import java.util.List;

/**
 * @author: zt
 * @date: 2020/7/21
 * @name:AssetsAdapter
 */
public class AssetsAdapter extends AutoRVAdapter {
    private AssetsFragmnt assetsFragmnt;
    private List<AssetsBean> list;

    public AssetsAdapter(AssetsFragmnt assetsFragmnt, List<AssetsBean> list) {
        super(assetsFragmnt.getContext(), list);
        this.assetsFragmnt = assetsFragmnt;
        this.list = list;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_assets_main;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        AssetsBean assetsBean = list.get(position);
        if ("1".equals(assetsBean.getCoinTypeId() + "")) {
            vh.getTextView(R.id.text_num_bc).setText("USDT");
        }else{
            vh.getTextView(R.id.text_num_bc).setText("BOX");
        }


        vh.getTextView(R.id.text_num_bc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(assetsFragmnt.getContext(), AssetsActivity.class);
                assetsFragmnt.startActivity(intent);
            }
        });
    }
    public void setData(List<AssetsBean> list) {
        this.list = list;
    }

}
