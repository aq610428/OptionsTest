package com.jkabe.app.box.adapter;

import android.content.Context;

import com.jkabe.box.R;
import com.jkabe.app.box.bean.Brand;
import com.jkabe.app.box.bean.BrandVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/26
 * @name:清单
 */
public class RightAdapter2 extends AutoRVAdapter {
    private List<BrandVo.ItemsBean> inventories = new ArrayList<>();
    private Context mContext;

    public RightAdapter2(Context context, List<BrandVo.ItemsBean> inventories) {
        super(context,inventories);
        this.mContext = context;
        this.inventories = inventories;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    public void setData(List<Brand> infos) {
        this.inventories = inventories;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_right_brand;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        BrandVo.ItemsBean inventory = inventories.get(position);
        vh.getTextView(R.id.text_name).setText(inventory.getFullName()+ "");
    }


}
