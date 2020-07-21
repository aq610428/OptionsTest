package com.jkabe.app.android.adapter;

import android.content.Context;
import com.jkabe.box.R;
import com.jkabe.app.android.bean.BrandVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/26
 * @name:清单
 */
public class RightAdapter extends AutoRVAdapter {
    private List<BrandVo> inventories = new ArrayList<>();
    private Context mContext;

    public RightAdapter(Context context, List<BrandVo> inventories) {
        super(context,inventories);
        this.mContext = context;
        this.inventories = inventories;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_right_brand;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        BrandVo inventory = inventories.get(position);
        vh.getTextView(R.id.text_name).setText(inventory.getBusiness().getFullName()+ "");
    }


}
