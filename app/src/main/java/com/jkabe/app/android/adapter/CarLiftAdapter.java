package com.jkabe.app.android.adapter;

import com.jkabe.box.R;
import com.jkabe.app.android.bean.LeftVo;
import com.jkabe.app.android.glide.GlideUtils;
import com.jkabe.app.android.ui.fragment.CarLeftFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/26
 * @name:清单
 */
public class CarLiftAdapter extends AutoRVAdapter {
    private List<LeftVo.ItemsBean> inventories = new ArrayList<>();
    private CarLeftFragment fragment;

    public CarLiftAdapter(CarLeftFragment fragment, List<LeftVo.ItemsBean> inventories) {
        super(fragment.getContext(), inventories);
        this.fragment = fragment;
        this.inventories = inventories;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_lefe_car;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        LeftVo.ItemsBean leftVo = inventories.get(position);
        vh.getTextView(R.id.text_name).setText(leftVo.getName());
        GlideUtils.setImageUrl(leftVo.getTagLogo(),vh.getImageView(R.id.iv_logo));
    }


}
